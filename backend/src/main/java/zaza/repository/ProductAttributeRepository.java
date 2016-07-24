package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
