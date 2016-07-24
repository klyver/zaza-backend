package zaza.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Set<Role> roles;

    @ManyToOne(optional = true)
    private Manufacturer manufacturer;

    public User() {
    }

    public User(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isManufacturer() {
        return roles.contains(Role.MANUFACTURER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
