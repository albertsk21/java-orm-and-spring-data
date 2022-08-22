package entities;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "fuel_type")
    private String fuelType;
    private String model;
    private BigDecimal price;
    private String type;

//   -------------------CONSTRUCTORS---------------------------
    public Vehicle() {}
    public Vehicle(String fuelType, String model, BigDecimal price, String type) {
        this.setFuelType(fuelType);
        this.setModel(model);
        this.setPrice(price);
        this.setType(type);
    }
//    ------------------SETTERS--------------------------------

    private void setId(Long id) {
        this.id = id;
    }
    private void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    private void setModel(String model) {
        this.model = model;
    }
    private void setPrice(BigDecimal price) {
        this.price = price;
    }
    private void setType(String type) {
        this.type = type;
    }

//    ------------------GETTERS--------------------------------
    public Long getId() {
        return id;
    }
    public String getFuelType() {
        return fuelType;
    }
    public String getModel() {
        return model;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
}
