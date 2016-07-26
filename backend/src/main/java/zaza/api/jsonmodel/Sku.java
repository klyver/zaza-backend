package zaza.api.jsonmodel;

import zaza.model.catalog.Dimension;
import zaza.model.catalog.SkuProductOptionValueXref;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sku {
    private String id;
    private String sourceId;
    private boolean defaultSku;
    private Dimension dimension;
    private BigDecimal weight;
    private BigDecimal price;
    private Long quantityInStock;
    private List<ProductOptionValue> productOptionValues;

    public Sku() {
    }

    public Sku(zaza.model.catalog.Sku sku) {
        this.id = sku.getId().toString();
        this.sourceId = sku.getSourceId();
        this.defaultSku = sku.isDefaultSku();
        this.dimension = sku.getDimension();
        this.weight = sku.getWeight();
        this.price = sku.getPrice();
        this.quantityInStock = sku.getQuantityInStock();
        this.productOptionValues = sku.getProductOptionValueXrefs().stream()
                .map(SkuProductOptionValueXref::getProductOptionValue)
                .sorted(new Comparator<zaza.model.catalog.ProductOptionValue>() {
                    @Override
                    public int compare(zaza.model.catalog.ProductOptionValue o1, zaza.model.catalog.ProductOptionValue o2) {
                        return o1.getProductOption().getDisplayOrder().compareTo(o2.getProductOption().getDisplayOrder());
                    }
                })
                .map(ProductOptionValue::new)
                .collect(Collectors.toList());
    }

    public zaza.model.catalog.Sku toDomainModel() {
        Long idAsLong = id != null ? Long.parseLong(id) : null;
        return new zaza.model.catalog.Sku(idAsLong, sourceId, defaultSku, new Dimension(dimension.getWidth(), dimension.getHeight(), dimension.getDepth()), weight, price, quantityInStock);
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

    public boolean isDefaultSku() {
        return defaultSku;
    }

    public void setDefaultSku(boolean defaultSku) {
        this.defaultSku = defaultSku;
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

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public List<ProductOptionValue> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValue> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }
}
