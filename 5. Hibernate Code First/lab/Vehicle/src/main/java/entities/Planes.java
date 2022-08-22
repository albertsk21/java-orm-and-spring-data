package entities;


import tools.Company;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Planes extends Vehicle{


    private String airline;
    @Column(name = "passenger_capacity")
    private int passengerCapacity;
    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    //   -------------------CONSTRUCTORS---------------------------

    public Planes(String airline, int passengerCapacity) {
        this.airline = airline;
        this.passengerCapacity = passengerCapacity;
    }

    public Planes(String fuelType, String model, BigDecimal price, String type, String airline, int passengerCapacity) {
        super(fuelType, model, price, type);
        this.airline = airline;
        this.passengerCapacity = passengerCapacity;
    }


    //    ------------------SETTERS--------------------------------

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }


    //    ------------------GETTERS--------------------------------


    public String getAirline() {
        return airline;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }
}
