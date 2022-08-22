package entities;

import tools.Driver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle{

    @Column(name = "load_capacity")
    private int loadCapacity;

    @ManyToMany
    private Set<Driver> drivers;

    //   -------------------CONSTRUCTORS---------------------------

    public Truck(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Truck(String fuelType, String model, BigDecimal price, String type, int loadCapacity) {
        super(fuelType, model, price, type);
        this.loadCapacity = loadCapacity;
    }


    //    ------------------SETTERS--------------------------------

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }


    //    ------------------GETTERS--------------------------------


    public int getLoadCapacity() {
        return loadCapacity;
    }
}
