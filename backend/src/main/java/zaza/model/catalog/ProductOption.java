package zaza.model.catalog;

import zaza.model.BaseIdentifiable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * For example, a product might have product options "Size" and "Color".
 *
 * For example, consider a shirt that is sold in 5 colors and 5 sizes.  For this example,
 * there would be 1 product. There would be 2 ProductOptions, 10 ProductOptionValues, and 25 Skus.
 */
@Entity
public class ProductOption extends BaseIdentifiable {

    @Id
    @GeneratedValue()
    private Long id;
    @Column(nullable = false)
    private String label;
    @Column(nullable = false)
    private Integer displayOrder;

    @OneToMany(mappedBy = "productOption")
    private List<ProductOptionXref> productXrefs;

    @OneToMany(mappedBy = "productOption")
    @OrderBy(value = "displayOrder")
    private List<ProductOptionValue> allowedValues;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<ProductOptionXref> getProductXrefs() {
        return productXrefs;
    }

    public void setProductXrefs(List<ProductOptionXref> productXrefs) {
        this.productXrefs = productXrefs;
    }

    public List<ProductOptionValue> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<ProductOptionValue> allowedValues) {
        this.allowedValues = allowedValues;
    }
}
