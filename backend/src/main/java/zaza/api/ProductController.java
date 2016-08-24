package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zaza.api.jsonmodel.Product;
import zaza.api.jsonmodel.ProductOption;
import zaza.api.jsonmodel.ProductOptionValue;
import zaza.model.Role;
import zaza.model.User;
import zaza.model.catalog.ProductAttribute;
import zaza.model.catalog.ProductOptionXref;
import zaza.model.catalog.Sku;
import zaza.model.catalog.SkuProductOptionValueXref;
import zaza.model.order.OrderItem;
import zaza.repository.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private ProductAttributeRepository productAttributeRepository;


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
    public Product createProduct(@RequestBody Product responseBody, HttpSession session) {
        if (responseBody.getSkus().stream()
                .filter(zaza.api.jsonmodel.Sku::isDefaultSku)
                .collect(Collectors.toList()).size() != 1) {
            throw new IllegalArgumentException("There must be exactly one defaultSku");
        }

        User user = (User) session.getAttribute("user");

        zaza.model.catalog.Product product = new zaza.model.catalog.Product();
        product.setManufacturer(user.getManufacturer());
        updateProductWithResponseBodyData(product, responseBody, user);
        final zaza.model.catalog.Product savedProduct = productRepository.save(product);
        updateProductAttributes(savedProduct, responseBody);
        addSkusAndProductOptions(savedProduct, responseBody);
        return new Product(productRepository.findOne(savedProduct.getId()));
    }

    @RequestMapping(value = "/api/products/{productId}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable String productId, @RequestBody Product responseBody, HttpSession session) {
        if (responseBody.getSkus().stream()
                .filter(zaza.api.jsonmodel.Sku::isDefaultSku)
                .collect(Collectors.toList()).size() != 1) {
            throw new IllegalArgumentException("There must be exactly one defaultSku");
        }

        User user = (User) session.getAttribute("user");
        zaza.model.catalog.Product product = productRepository.findOne(Long.parseLong(productId));
        updateProductWithResponseBodyData(product, responseBody, user);
        updateProductAttributes(product, responseBody);
        product = productRepository.save(product);

        //////////////////////////////////////////////
        Map<ProductOptionXref, Boolean> foundProductOptions = new HashMap<>();
        for (ProductOptionXref productOptionXref : product.getProductOptions()) {
            foundProductOptions.put(productOptionXref, false);
        }
        for (ProductOption productOption : responseBody.getProductOptions()) {
            zaza.model.catalog.ProductOption modelProductOption = productOptionRepository.findOne(Long.parseLong(productOption.getId()));
            List<ProductOptionXref> productOptionXref = productOptionXrefRepository.findByProductAndProductOption(product, modelProductOption);
            if (! productOptionXref.isEmpty()) {
                foundProductOptions.put(productOptionXref.get(0), true);
            } else {
                productOptionXrefRepository.save(new ProductOptionXref(product, modelProductOption));
            }
        }

        productOptionXrefRepository.delete(foundProductOptions.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
        //////////////////////////////////////////////


        //////////////////////////////////////////////
        List<SkuProductOptionValueXref> existingSkuProductOptionValueXrefs = product.getSkus().stream()
                .map(Sku::getProductOptionValueXrefs)
                .flatMap(Set::stream)
                .collect(Collectors.toList());
        Map<SkuProductOptionValueXref, Boolean> foundSkuProductOptionValueXrefs = new HashMap<>();
        for (SkuProductOptionValueXref existingSkuProductOptionValueXref : existingSkuProductOptionValueXrefs) {
            foundSkuProductOptionValueXrefs.put(existingSkuProductOptionValueXref, false);
        }
        Map<Sku, Boolean> foundSkus = new HashMap<>();
        for (Sku sku : product.getSkus()) {
            foundSkus.put(sku, false);
        }

        for (zaza.api.jsonmodel.Sku jsonSku : responseBody.getSkus()) {
            if (jsonSku.getId() != null) {
                Sku existingSku = skuRepository.findOne(Long.parseLong(jsonSku.getId()));
                if (existingSku != null) {
                    foundSkus.put(existingSku, true);
                }
            }

            Sku sku = jsonSku.toDomainModel();
            sku.setProduct(product);
            skuRepository.save(sku);

            sku.getProductOptionValueXrefs().clear();
            for (ProductOptionValue productOptionValueJson : jsonSku.getProductOptionValues()) {
                zaza.model.catalog.ProductOptionValue productOptionValue = productOptionValueRepository.findOne(Long.parseLong(productOptionValueJson.getId()));
                List<SkuProductOptionValueXref> existingSkuProductOptionValueXref = skuProductOptionValueXrefRepository.findBySkuAndProductOptionValue(sku, productOptionValue);
                if (!existingSkuProductOptionValueXref.isEmpty()) {
                    foundSkuProductOptionValueXrefs.put(existingSkuProductOptionValueXref.get(0), true);
                } else {
                    SkuProductOptionValueXref newSkuProductOptionValueXref = skuProductOptionValueXrefRepository.save(new SkuProductOptionValueXref(sku, productOptionValue));
                }
            }
        }
        skuProductOptionValueXrefRepository.delete(foundSkuProductOptionValueXrefs.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));

        List<Sku> toDelete = foundSkus.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for (Sku sku : toDelete) {
            List<OrderItem> ordersForSku = orderItemRepository.findBySku(sku);
            if (!ordersForSku.isEmpty()) {
                throw new IllegalArgumentException("cannot delete sku that has orders");
            }
        }
        skuRepository.delete(toDelete);
        ////////////////////////////////////////

        return new Product(productRepository.findOne(Long.parseLong(productId)));
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
            product.setDescriptionMandarin(responseBody.getDescriptionMandarin());
            product.setLongDescriptionMandarin(responseBody.getLongDescriptionMandarin());
            product.setApproved(responseBody.isApproved());
        } else {
            product.setApproved(false);
        }
        product.setCategory(categoryRepository.findOne(Long.parseLong(responseBody.getCategoryId())));
    }

    private void updateProductAttributes(zaza.model.catalog.Product product, Product responseBody) {
        productAttributeRepository.delete(product.getProductAttributes().values());
        product.getProductAttributes().clear();
        for (zaza.api.jsonmodel.ProductAttribute jsonProductAttribute : responseBody.getProductAttributes()) {
            ProductAttribute productAttribute = new ProductAttribute(jsonProductAttribute.getName(), jsonProductAttribute.getValue(), product);
            productAttribute = productAttributeRepository.save(productAttribute);
            product.getProductAttributes().put(jsonProductAttribute.getName(), productAttribute);
        }
    }
}
