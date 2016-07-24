package zaza.model.order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"Order\"")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    protected Date dateCreated;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


    /**
     * The unique number associated with this Order. Generally preferred to use instead of just using id
     * since that exposes unwanted information about your database.
     */
    @Column(nullable = false, updatable = false)
    private String orderNumber;

    @Embedded
    private Address shippingAddress = new Address();

    /**
     * Returns the subtotal price for the order.  The subtotal price is the price of all order items
     * with item offers applied.  The subtotal does not take into account the order promotions, shipping costs or any
     * taxes that apply to this order.
     */
    @Column(nullable = false)
    private BigDecimal subTotal;

    /**
     * The grand total of this {@link Order} which includes all shipping costs and taxes, as well as any adjustments from
     * promotions.
     */
    @Column(nullable = false)
    private BigDecimal total;



//    private OrderPayment payment; //TODO
//    private Customer customer; //TODO


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
