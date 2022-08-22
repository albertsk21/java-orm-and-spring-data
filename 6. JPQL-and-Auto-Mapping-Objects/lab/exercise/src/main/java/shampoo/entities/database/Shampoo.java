package shampoo.entities.database;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private BigDecimal price;
    private int size;
    @ManyToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;

    public Label getLabel() {
        return label;
    }

    public Shampoo() {
    }

    public Shampoo(String brand, BigDecimal price, int size, Label label) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = label;
    }


    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
}
