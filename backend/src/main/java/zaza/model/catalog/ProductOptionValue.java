package zaza.model.catalog;

import zaza.model.BaseIdentifiable;
import javax.persistence.*;

@Entity
public class ProductOptionValue extends BaseIdentifiable {

    @Id
    @GeneratedValue
    private Long id;

    // e.g. "red", "blue"
    @Column(nullable = false)
    private String attributeValue;
    @Column(nullable = false)
    private Long displayOrder;
    @ManyToOne(optional = false)
    private ProductOption productOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }
}
