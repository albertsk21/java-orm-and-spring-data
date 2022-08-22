package tools;


import entities.Truck;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private BigInteger id;

    @Column(name = "full_name")
    private String fullName;
    @ManyToMany(mappedBy = "drivers")
    private Set<Truck> trucks;


    //   -------------------CONSTRUCTORS---------------------------

    public Driver() {}

    public Driver(String fullName) {
      this.setFullName(fullName);
    }
    //    ------------------SETTERS--------------------------------

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTrucks(Set<Truck> trucks) {
        this.trucks = trucks;
    }

    //    ------------------GETTERS--------------------------------


    public BigInteger getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<Truck> getTrucks() {
        return trucks;
    }
}
