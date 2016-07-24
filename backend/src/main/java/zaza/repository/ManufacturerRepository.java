package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {


}
