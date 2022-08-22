package car.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    @ManyToMany(mappedBy = "parts",fetch = FetchType.EAGER)
    private Set<Car> cars = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public Part() {
    }

    public Part(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
    public int getQuantity() {
        return quantity;
    }
    public Set<Car> getCars() {
        return cars;
    }
}
