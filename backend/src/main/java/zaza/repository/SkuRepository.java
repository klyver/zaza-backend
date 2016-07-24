package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.Sku;

public interface SkuRepository extends JpaRepository<Sku, Long> {
}
