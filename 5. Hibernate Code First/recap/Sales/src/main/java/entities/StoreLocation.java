package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "stores_location")
public class StoreLocation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "location_name")
    private String locationName;
    @OneToMany(mappedBy = "storeLocation")
    Set<Sale> sales;

    public StoreLocation() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public int getId() {
        return id;
    }
    public String getLocationName() {
        return locationName;
    }
    public Set<Sale> getSales() {
        return sales;
    }
}
