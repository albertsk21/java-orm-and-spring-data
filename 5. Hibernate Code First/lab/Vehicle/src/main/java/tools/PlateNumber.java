package tools;


import entities.Car;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private BigInteger id;
    private String number;

    @OneToOne
    private Car car;

    //   -------------------CONSTRUCTORS---------------------------
    public PlateNumber() {}

    public PlateNumber(String number) {
        this.number = number;
    }

    //    ------------------SETTERS--------------------------------


    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    //    ------------------GETTERS--------------------------------


    public BigInteger getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
