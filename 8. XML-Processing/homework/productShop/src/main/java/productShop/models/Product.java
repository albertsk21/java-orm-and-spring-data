package productShop.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Category> categories =  new LinkedHashSet<>();


    public Product() {
    }

    public Product(String name, BigDecimal price, User buyer, User seller) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories =  new LinkedHashSet<>();
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Set<Category> getCategories() {
        return categories;
    }
    public User getSeller() {
        return seller;
    }
    public User getBuyer() {
        return buyer;
    }


}
