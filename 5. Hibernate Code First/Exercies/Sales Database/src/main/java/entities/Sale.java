package entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_location_id")
    private StoreLocation storeLocation;
    private Date date;

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    //    -----------------------CONSTRUCTOR----------------------------
    public Sale() {}

//    -----------------------SETTERS----------------------------------

    public void setId(int id) {
        this.id = id;
    }

//    -----------------------GETTERS----------------------------------

    public int getId() {
        return id;
    }

}
