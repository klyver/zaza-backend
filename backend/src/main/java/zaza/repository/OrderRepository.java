package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
