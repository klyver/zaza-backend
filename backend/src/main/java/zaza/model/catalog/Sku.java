package zaza.model.catalog;

import zaza.model.BaseIdentifiable;
import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sku extends BaseIdentifiable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String sourceId;
    @ManyToOne
    private Product product;
    @Column
    private boolean defaultSku;
    @Embedded
    private Dimension dimension = new Dimension();
    @Column(nullable = false)
    private BigDecimal weight;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Long quantityInStock;

    @OneToMany(mappedBy = "sku")
    private Set<SkuProductOptionValueXref> productOptionValueXrefs = new HashSet<SkuProductOptionValueXref>();

    public Sku() {
    }

    public Sku(Long id, String sourceId, boolean defaultSku, Dimension dimension, BigDecimal weight, BigDecimal price, Long quantityInStock) {
        this.id = id;
        this.sourceId = sourceId;
        this.defaultSku = defaultSku;
        this.dimension = dimension;
        this.weight = weight;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<SkuProductOptionValueXref> getProductOptionValueXrefs() {
        return productOptionValueXrefs;
    }

    public void setProductOptionValueXrefs(Set<SkuProductOptionValueXref> productOptionValueXrefs) {
        this.productOptionValueXrefs = productOptionValueXrefs;
    }

    public boolean isDefaultSku() {
        return defaultSku;
    }

    public void setDefaultSku(boolean defaultSku) {
        this.defaultSku = defaultSku;
    }
}
