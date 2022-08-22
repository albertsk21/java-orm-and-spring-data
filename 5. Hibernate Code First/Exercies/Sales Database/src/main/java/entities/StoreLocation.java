package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name")
    private String locationName;
    @OneToMany(mappedBy = "storeLocation",cascade = CascadeType.ALL  ,fetch = FetchType.LAZY)
    private Set<Sale> sales;

//    -----------------------CONSTRUCTOR-----------------------------

    public StoreLocation() {}
    public StoreLocation(String locationName) {
        this.setLocationName(locationName);
    }

    //    -------------------------SETTERS-------------------------------
    public void setId(int id) {
        this.id = id;
    }
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
//    -------------------------GETTERS-------------------------------
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
