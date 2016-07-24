package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zaza.api.jsonmodel.Product;
import zaza.api.jsonmodel.ProductOption;
import zaza.api.jsonmodel.ProductOptionValue;
import zaza.model.Role;
import zaza.model.User;
import zaza.model.catalog.ProductOptionXref;
import zaza.model.catalog.Sku;
import zaza.model.catalog.SkuProductOptionValueXref;
import zaza.model.order.OrderItem;
import zaza.repository.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Transactional
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOptionXrefRepository productOptionXrefRepository;
    @Autowired
    private SkuProductOptionValueXrefRepository skuProductOptionValueXrefRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private ProductOptionRepository productOptionRepository;
    @Autowired
    private ProductOptionValueRepository productOptionValueRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;


    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public List<Product> getProducts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRoles().contains(Role.ADMIN)) {
            return productRepository.findAll().stream().map(Product::new).collect(Collectors.toList());
        } else if (user.getRoles().contains(Role.MANUFACTURER)) {
            return productRepository.findByManufacturer(user.getManufacturer()).stream().map(Product::new).collect(Collectors.toList());
        } else {
            throw new RuntimeException("should never happen");
        }
    }

    @RequestMapping(value = "/api/products/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable String productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        zaza.model.catalog.Product product = productRepository.findOne(Long.parseLong(productId));
        if (user.isManufacturer() && ! user.getManufacturer().equals(product.getManufacturer())) {
            throw new IllegalArgumentException("Product does not belong to manufacturer");
        }
        return new Product(productRepository.findOne(Long.parseLong(productId)));
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public String createProduct(@RequestBody Product responseBody, HttpSession session) {
        if (responseBody.getSkus().stream()
                .filter(zaza.api.jsonmodel.Sku::isDefaultSku)
                .collect(Collectors.toList()).size() != 1) {
            throw new IllegalArgumentException("There must be exactly one defaultSku");
        }

        User user = (User) session.getAttribute("user");

        zaza.model.catalog.Product product = new zaza.model.catalog.Product();
        product.setManufacturer(user.getManufacturer());
        updateProductWithResponseBodyData(product, responseBody, user);
        product.setCategory(categoryRepository.findOne(1L));
        final zaza.model.catalog.Product savedProduct = productRepository.save(product);
        addSkusAndProductOptions(savedProduct, responseBody);
        return savedProduct.getId().toString();
    }

    @RequestMapping(value = "/api/products/{productId}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable String productId, @RequestBody Product responseBody, HttpSession session) {
        if (responseBody.getSkus().stream()
                .filter(zaza.api.jsonmodel.Sku::isDefaultSku)
                .collect(Collectors.toList()).size() != 1) {
            throw new IllegalArgumentException("There must be exactly one defaultSku");
        }

        User user = (User) session.getAttribute("user");
        zaza.model.catalog.Product product = productRepository.findOne(Long.parseLong(productId));

        // delete all existing references. This is extremely inefficient, but it works for now
        productOptionXrefRepository.delete(product.getProductOptions());
        List<SkuProductOptionValueXref> skuProductOptionValueXrefs = product.getSkus().stream()
                .map(Sku::getProductOptionValueXrefs)
                .flatMap(Set::stream)
                .collect(Collectors.toList());
        skuProductOptionValueXrefRepository.delete(skuProductOptionValueXrefs);
        for (Sku sku : product.getSkus()) {
            List<OrderItem> ordersForSku = orderItemRepository.findBySku(sku);
            if (!ordersForSku.isEmpty()) {
                throw new IllegalArgumentException("cannot delete sku that has orders");
            }
        }
        skuRepository.delete(product.getSkus());

        updateProductWithResponseBodyData(product, responseBody, user);
        final zaza.model.catalog.Product savedProduct = productRepository.save(product);
        addSkusAndProductOptions(savedProduct, responseBody);
    }

    private void addSkusAndProductOptions(zaza.model.catalog.Product product, Product responseBody) {
        List<ProductOptionXref> productOptionXrefs = responseBody.getProductOptions().stream()
                .map((ProductOption productOption) ->
                        new ProductOptionXref(product, productOptionRepository.findOne(Long.parseLong(productOption.getId()))))
                .collect(Collectors.toList());
        productOptionXrefRepository.save(productOptionXrefs);

        for (zaza.api.jsonmodel.Sku jsonSku : responseBody.getSkus()) {
            Sku sku = jsonSku.toDomainModel();
            sku.setProduct(product);
            skuRepository.save(sku);
            for (ProductOptionValue productOptionValue : jsonSku.getProductOptionValues()) {
                skuProductOptionValueXrefRepository.save(new SkuProductOptionValueXref(sku, productOptionValueRepository.findOne(Long.parseLong(productOptionValue.getId()))));
            }
        }
    }

    private void updateProductWithResponseBodyData(zaza.model.catalog.Product product, Product responseBody, User user ) {
        product.setSourceId(responseBody.getSourceId());
        product.setName(responseBody.getName());
        product.setDescription(responseBody.getDescription());
        product.setLongDescription(responseBody.getLongDescription());

        if (user.isAdmin()) {
            product.setNameMandarin(responseBody.getNameMandarin());
            product.setDescriptionMandarin(responseBody.getNameMandarin());
            product.setLongDescriptionMandarin(responseBody.getLongDescriptionMandarin());
            product.setApproved(responseBody.isApproved());
        } else {
            product.setApproved(false);
        }
    }
}
