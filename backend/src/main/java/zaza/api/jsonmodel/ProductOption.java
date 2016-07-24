package zaza.api.jsonmodel;

import java.util.List;
import java.util.stream.Collectors;

public class ProductOption {

    private String id;
    private String label;
    private List<ProductOptionValue> productOptionValues;

    public ProductOption() {
    }

    public ProductOption(zaza.model.catalog.ProductOption productOption) {
        this.id = productOption.getId().toString();
        this.label = productOption.getLabel();
        this.productOptionValues = productOption.getAllowedValues().stream().map(ProductOptionValue::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ProductOptionValue> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValue> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }
}
