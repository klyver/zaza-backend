package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductAttribute {

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String value;

    public ProductAttribute() {
    }

    public ProductAttribute(zaza.model.catalog.ProductAttribute productAttribute) {
        this.name = productAttribute.getName();
        this.value = productAttribute.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
