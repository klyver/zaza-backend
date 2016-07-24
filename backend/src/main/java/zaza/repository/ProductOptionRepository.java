package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
