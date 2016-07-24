package zaza.model.catalog;

import zaza.model.BaseIdentifiable;

import javax.persistence.*;

@Entity
public class ProductOptionXref extends BaseIdentifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional=false)
    private Product product;

    @ManyToOne(optional=false)
    private ProductOption productOption;

    public ProductOptionXref() {
    }

    public ProductOptionXref(Product product, ProductOption productOption) {
        this.product = product;
        this.productOption = productOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }
}
