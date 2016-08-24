package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductOptionValue {

    @ApiModelProperty(required = true)
    private String id;
    @ApiModelProperty(required = true)
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
