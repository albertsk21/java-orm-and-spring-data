package entities;

import tools.PlateNumber;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    private int seats;
    @OneToOne(mappedBy = "car",fetch = FetchType.EAGER)
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;
    //   -------------------CONSTRUCTORS---------------------------
    public Car(int seats) {
        this.seats = seats;
    }
    public Car(String fuelType, String model, BigDecimal price, String type, int seats) {
        super(fuelType, model, price, type);
        this.seats = seats;
    }
    //    ------------------SETTERS--------------------------------
    public void setSeats(int seats) {
        this.seats = seats;
    }
    //    ------------------GETTERS--------------------------------
    public int getSeats() {
        return seats;
    }
}
