package zaza.api.jsonmodel;

import zaza.model.order.OrderItemStatus;

public class OrderItem {

    private String orderId;
    private String id;
    private String skuId;
    private String skuSourceId;
    private int quantity;
    private OrderItemStatus status;

    public OrderItem() {
    }

    public OrderItem(zaza.model.order.OrderItem orderItem) {
        this.orderId = orderItem.getOrder().getId().toString();
        this.id = orderItem.getId().toString();
        this.skuId = orderItem.getSku().getId().toString();
        this.skuSourceId = orderItem.getSku().getSourceId();
        this.quantity = orderItem.getQuantity();
        this.status = orderItem.getStatus();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuSourceId() {
        return skuSourceId;
    }

    public void setSkuSourceId(String skuSourceId) {
        this.skuSourceId = skuSourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }
}
