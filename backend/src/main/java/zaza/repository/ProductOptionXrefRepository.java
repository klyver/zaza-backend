package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.catalog.Product;
import zaza.model.catalog.ProductOption;
import zaza.model.catalog.ProductOptionXref;

import java.util.List;

public interface ProductOptionXrefRepository extends JpaRepository<ProductOptionXref, Long> {

    List<ProductOptionXref> findByProductAndProductOption(Product product, ProductOption productOption);

}
