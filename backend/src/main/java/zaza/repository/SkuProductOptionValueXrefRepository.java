package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.ProductOptionValue;
import zaza.model.catalog.Sku;
import zaza.model.catalog.SkuProductOptionValueXref;

import java.util.List;

public interface SkuProductOptionValueXrefRepository extends JpaRepository<SkuProductOptionValueXref, Long> {

    List<SkuProductOptionValueXref> findBySkuAndProductOptionValue(Sku sku, ProductOptionValue productOptionValue);

}
