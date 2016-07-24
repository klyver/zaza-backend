package zaza.model;

import java.io.Serializable;

public abstract class BaseIdentifiable implements Serializable {

    public abstract Long getId();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getId() == null){
            return this == obj;
        } else if (obj instanceof BaseIdentifiable) {
            BaseIdentifiable otherIdentifiable = (BaseIdentifiable) obj;
            return getId().equals(otherIdentifiable.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }
}