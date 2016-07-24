package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.Manufacturer;
import zaza.model.catalog.Sku;
import zaza.model.order.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findBySkuProductManufacturerOrderByOrderId(Manufacturer manufacturer);

    List<OrderItem> findBySku(Sku sku);

}
