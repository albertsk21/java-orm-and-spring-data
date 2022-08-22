package car.models;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    @Column(name = "travelled_distance")
    private Long travelledDistance;
    @ManyToMany( fetch = FetchType.EAGER)
    private Set<Part> parts = new LinkedHashSet<>();
    @OneToMany(mappedBy = "car", cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Sale> sales = new LinkedHashSet<>();



    public Car() {
    }

    public Car(String make, String model, Long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }


    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Long getId() {
        return id;
    }
    public Set<Part> getParts() {
        return parts;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
