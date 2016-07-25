package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zaza.api.jsonmodel.Category;
import zaza.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/api/categories/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable String categoryId) {
        return new Category(categoryRepository.findOne(Long.parseLong(categoryId)));
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryRepository.findByParent(null).stream().map(Category::new).collect(Collectors.toList());
    }

}
