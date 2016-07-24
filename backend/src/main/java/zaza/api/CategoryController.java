package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zaza.model.catalog.Category;
import zaza.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/api/categories/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryRepository.findByParent(null);
    }

}
