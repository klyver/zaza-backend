package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.Manufacturer;
import zaza.model.catalog.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByManufacturer(Manufacturer manufacturer);

}
