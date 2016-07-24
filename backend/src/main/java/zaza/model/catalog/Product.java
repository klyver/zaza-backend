package zaza.model.catalog;

import zaza.model.BaseIdentifiable;
import zaza.model.Manufacturer;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Product extends BaseIdentifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String sourceId;

    @Column
    private boolean approved;

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private String name;
    @Column
    private String nameMandarin;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String descriptionMandarin;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String longDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescriptionMandarin;


    @OneToMany(mappedBy = "product")
    private List<Sku> skus = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductOptionXref> productOptions = new ArrayList<>();

    //    public Map<String, Media> getMedia();

    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @MapKey(name = "name")
    private Map<String, ProductAttribute> productAttributes = new HashMap<>();


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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getNameMandarin() {
        return nameMandarin;
    }

    public void setNameMandarin(String nameMandarin) {
        this.nameMandarin = nameMandarin;
    }

    public String getDescriptionMandarin() {
        return descriptionMandarin;
    }

    public void setDescriptionMandarin(String descriptionMandarin) {
        this.descriptionMandarin = descriptionMandarin;
    }

    public String getLongDescriptionMandarin() {
        return longDescriptionMandarin;
    }

    public void setLongDescriptionMandarin(String longDescriptionMandarin) {
        this.longDescriptionMandarin = longDescriptionMandarin;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Map<String, ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Map<String, ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public List<ProductOptionXref> getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(List<ProductOptionXref> productOptions) {
        this.productOptions = productOptions;
    }
}
