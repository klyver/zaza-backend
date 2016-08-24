package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zaza.model.order.OrderItemStatus;

@ApiModel
public class OrderItem {

    @ApiModelProperty(required = true)
    private String orderId;
    @ApiModelProperty(required = true)
    private String id;
    @ApiModelProperty(required = true)
    private String skuId;
    @ApiModelProperty(required = true)
    private String skuSourceId;
    @ApiModelProperty(required = true)
    private int quantity;
    @ApiModelProperty(required = true)
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
