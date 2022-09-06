package softuni.workshop.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    private String authority;
    private List<User> users;


    public Role() {
    }

    public String getAuthority() {
        return authority;
    }

    public Role setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    public List<User> getUsers() {
        return users;
    }

    public Role setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
