package shopProducts.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> productsBuyer;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> productsSeller;
    @ManyToMany(mappedBy = "friends",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> friends;


    public User() {
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setProductsBuyer(Set<Product> productsBuyer) {
        this.productsBuyer = productsBuyer;
    }
    public void setProductsSeller(Set<Product> productsSeller) {
        this.productsSeller = productsSeller;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }



    public Set<Product> getProductsBuyer() {
        return productsBuyer;
    }
    public Set<Product> getProductsSeller() {
        return productsSeller;
    }
    public Set<User> getUsers() {
        return users;
    }
    public Set<User> getFriends() {
        return friends;
    }
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Integer getAge() {
        return age;
    }
}
