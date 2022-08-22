package softuni.workshop.data.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String authority;
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<User> users = new LinkedHashSet<>();


    public Role() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
    public Set<User> getUsers() {
        return users;
    }
}
