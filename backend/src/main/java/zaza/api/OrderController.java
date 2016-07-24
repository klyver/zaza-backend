package zaza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zaza.api.jsonmodel.OrderItem;
import zaza.model.Role;
import zaza.model.User;
import zaza.model.order.Address;
import zaza.model.order.OrderItemStatus;
import zaza.repository.OrderItemRepository;
import zaza.repository.OrderRepository;
import zaza.repository.SkuRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private SkuRepository skuRepository;

    @RequestMapping(value = "/api/orderItems", method = RequestMethod.GET)
    public List<OrderItem> getProducts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRoles().contains(Role.ADMIN)) {
            return orderItemRepository.findAll().stream().map(OrderItem::new).collect(Collectors.toList());
        } else if (user.getRoles().contains(Role.MANUFACTURER)) {
            return orderItemRepository.findBySkuProductManufacturerOrderByOrderId(user.getManufacturer()).stream().map(OrderItem::new).collect(Collectors.toList());
        } else {
            throw new RuntimeException("should never happen");
        }
    }

    @RequestMapping(value = "/api/orderItems/{orderItemId}/confirm", method = RequestMethod.POST)
    public void confirmOrderItem(@PathVariable String orderItemId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!user.isManufacturer()) {
            throw new RuntimeException("should never happen");
        }
        zaza.model.order.OrderItem orderItem = orderItemRepository.findOne(Long.parseLong(orderItemId));
        if (! orderItem.getSku().getProduct().getManufacturer().equals(user.getManufacturer())) {
            throw new IllegalArgumentException("sku does not belong to manufacturer");
        }
        orderItem.setStatus(OrderItemStatus.CONFIRMED_BY_MANUFACTURER);
        orderItemRepository.save(orderItem);
        orderItem.getSku().setQuantityInStock(orderItem.getSku().getQuantityInStock() - 1);
        skuRepository.save(orderItem.getSku());
    }

    @RequestMapping(value = "/api/order/createtestorder", method = RequestMethod.POST)
    public void createNewTestOrder(HttpSession session) {
        zaza.model.order.Order order = new zaza.model.order.Order();
        order.setDateCreated(new Date());
        order.setOrderNumber("dkdkiekd");
        Address address = new Address();
        address.setAddress("Kinavej 18");
        address.setCity("Guangzhou");
        address.setFullName("Kim");
        address.setPhone("12345678");
        address.setPostalCode("4567");
        address.setProvince("Guangdong");
        order.setShippingAddress(address);
        order.setSubTotal(new BigDecimal(400));
        order.setTotal(new BigDecimal(500));
        orderRepository.save(order);

        User user = (User) session.getAttribute("user");
        if (user.isManufacturer() && user.getManufacturer().getId().equals(1L)) {
            zaza.model.order.OrderItem orderItemOne = new zaza.model.order.OrderItem();
            orderItemOne.setOrder(order);
            orderItemOne.setStatus(OrderItemStatus.SUBMITTED_BY_CUSTOMER);
            orderItemOne.setPrice(new BigDecimal(100));
            orderItemOne.setSku(skuRepository.findOne(4L));
            orderItemOne.setQuantity(1);
            orderItemRepository.save(orderItemOne);

            zaza.model.order.OrderItem orderItemTwo = new zaza.model.order.OrderItem();
            orderItemTwo.setOrder(order);
            orderItemTwo.setStatus(OrderItemStatus.SUBMITTED_BY_CUSTOMER);
            orderItemTwo.setPrice(new BigDecimal(300));
            orderItemTwo.setSku(skuRepository.findOne(7L));
            orderItemTwo.setQuantity(1);
            orderItemRepository.save(orderItemTwo);
        } else {
            zaza.model.order.OrderItem orderItem = new zaza.model.order.OrderItem();
            orderItem.setOrder(order);
            orderItem.setStatus(OrderItemStatus.SUBMITTED_BY_CUSTOMER);
            orderItem.setPrice(new BigDecimal(400));
            orderItem.setSku(skuRepository.findOne(16L));
            orderItem.setQuantity(1);
            orderItemRepository.save(orderItem);

        }
    }

}
