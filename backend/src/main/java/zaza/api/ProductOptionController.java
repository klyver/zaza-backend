package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zaza.api.jsonmodel.ProductOption;
import zaza.repository.ProductOptionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class ProductOptionController {

    private ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductOptionController(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    @RequestMapping(value = "/api/productOptions", method = RequestMethod.GET)
    public List<ProductOption> getProductOptions() {
        return productOptionRepository.findAll().stream().map(ProductOption::new).collect(Collectors.toList());
    }

}
