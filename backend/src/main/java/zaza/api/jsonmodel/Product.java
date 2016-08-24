package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import zaza.model.catalog.ProductOptionXref;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class Product {
    @ApiModelProperty(required = true)
    private String id;
    @ApiModelProperty(required = true)
    private String sourceId;
    @ApiModelProperty(required = true)
    private boolean approved;

    @ApiModelProperty(required = true)
    private String manufacturer;
    @ApiModelProperty(required = true)
    private String name;

    private String nameMandarin;
    @ApiModelProperty(required = true)
    private String description;
    private String descriptionMandarin;
    @ApiModelProperty(required = true)
    private String longDescription;
    private String longDescriptionMandarin;

    @ApiModelProperty(required = true)
    private String categoryId;
    @ApiModelProperty(required = true)
    private List<ProductAttribute> productAttributes;
    @ApiModelProperty(required = true)
    private List<Sku> skus;
    @ApiModelProperty(required = true)
    private List<ProductOption> productOptions;

    public Product() {
    }

    public Product(zaza.model.catalog.Product product) {
        this.id = product.getId().toString();
        this.sourceId = product.getSourceId();
        this.approved = product.isApproved();
        this.manufacturer = product.getManufacturer().getName();
        this.name = product.getName();
        this.nameMandarin = product.getNameMandarin();
        this.description = product.getDescription();
        this.descriptionMandarin = product.getDescriptionMandarin();
        this.longDescription = product.getLongDescription();
        this.longDescriptionMandarin = product.getLongDescriptionMandarin();
        this.categoryId = product.getCategory().getId().toString();
        this.productAttributes = product.getProductAttributes().values().stream().map(ProductAttribute::new).sorted(new Comparator<ProductAttribute>() {
            @Override
            public int compare(ProductAttribute o1, ProductAttribute o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList());
        this.skus = product.getSkus().stream().map(Sku::new).collect(Collectors.toList());
        this.productOptions = product.getProductOptions().stream().map((ProductOptionXref xref) -> new ProductOption(xref.getProductOption())).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMandarin() {
        return nameMandarin;
    }

    public void setNameMandarin(String nameMandarin) {
        this.nameMandarin = nameMandarin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionMandarin() {
        return descriptionMandarin;
    }

    public void setDescriptionMandarin(String descriptionMandarin) {
        this.descriptionMandarin = descriptionMandarin;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescriptionMandarin() {
        return longDescriptionMandarin;
    }

    public void setLongDescriptionMandarin(String longDescriptionMandarin) {
        this.longDescriptionMandarin = longDescriptionMandarin;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public List<ProductOption> getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(List<ProductOption> productOptions) {
        this.productOptions = productOptions;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}
