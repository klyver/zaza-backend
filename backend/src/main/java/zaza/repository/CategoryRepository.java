package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParent(Category parent);
}
