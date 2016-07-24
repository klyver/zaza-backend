package zaza.model.catalog;

import zaza.model.BaseIdentifiable;

import javax.persistence.*;

@Entity
public class SkuProductOptionValueXref extends BaseIdentifiable{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional=false)
    private Sku sku;

    @ManyToOne(optional=false)
    private ProductOptionValue productOptionValue;

    public SkuProductOptionValueXref() {
    }

    public SkuProductOptionValueXref(Sku sku, ProductOptionValue productOptionValue) {
        this.sku = sku;
        this.productOptionValue = productOptionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }
}
