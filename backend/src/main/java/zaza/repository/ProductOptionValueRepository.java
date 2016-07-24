package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.ProductOptionValue;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {
}
