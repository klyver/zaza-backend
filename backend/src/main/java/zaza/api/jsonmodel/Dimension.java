package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class Dimension {

    @ApiModelProperty(required = true)
    private BigDecimal width;
    @ApiModelProperty(required = true)
    private BigDecimal height;
    @ApiModelProperty(required = true)
    private BigDecimal depth;

    public Dimension() {
    }

    public Dimension(zaza.model.catalog.Dimension dimension) {
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
        this.depth = dimension.getDepth();
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

}
