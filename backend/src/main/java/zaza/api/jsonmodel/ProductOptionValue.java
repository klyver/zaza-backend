package zaza.api.jsonmodel;

public class ProductOptionValue {

    private String id;
    private String attributeValue;

    public ProductOptionValue() {
    }

    public ProductOptionValue(zaza.model.catalog.ProductOptionValue productOptionValue) {
        this.id = productOptionValue.getId().toString();
        this.attributeValue = productOptionValue.getAttributeValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
