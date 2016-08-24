package zaza.api.jsonmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class Category {

    @ApiModelProperty(required = true)
    private String id;
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private List<Category> children;

    public Category() {
    }

    public Category(zaza.model.catalog.Category category) {
        this.id = category.getId().toString();
        this.name = category.getName();
        this.children = category.getChildren().stream().map(Category::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
