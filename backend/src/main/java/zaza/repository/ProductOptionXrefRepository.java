package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.ProductOptionXref;

public interface ProductOptionXrefRepository extends JpaRepository<ProductOptionXref, Long> {
}
