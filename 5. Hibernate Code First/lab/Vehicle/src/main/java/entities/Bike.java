package entities;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{


    //   -------------------CONSTRUCTORS---------------------------

    public Bike() {
    }
    public Bike(String fuelType, String model, BigDecimal price, String type) {
        super(fuelType, model, price, type);
    }
}
