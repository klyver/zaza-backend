package zaza.api.jsonmodel;

import java.util.List;
import java.util.stream.Collectors;

public class Category {

    private String id;
    private String name;
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
