package zaza.model.catalog;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class Dimension implements Serializable {

    @Column(nullable = false)
    private BigDecimal width;
    @Column(nullable = false)
    private BigDecimal height;
    @Column(nullable = false)
    private BigDecimal depth;

    public Dimension() {
    }

    public Dimension(BigDecimal width, BigDecimal height, BigDecimal depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
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
