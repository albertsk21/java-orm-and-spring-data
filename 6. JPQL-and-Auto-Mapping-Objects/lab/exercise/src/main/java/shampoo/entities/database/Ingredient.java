package shampoo.entities.database;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToMany(mappedBy = "ingredients",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Shampoo> shampoos;


    public Ingredient() {
    }

    public Ingredient(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.shampoos = new LinkedHashSet<>();
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
    public void addShampoo(Shampoo shampoo){
        this.shampoos.add(shampoo);
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

    public Set<Shampoo> getShampoos() {
        return shampoos;
    }
}
