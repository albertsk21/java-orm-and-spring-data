package entities;

import javax.persistence.*;

@Entity @Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
    @ManyToOne
    @JoinColumn(name = "store_location_id")
    StoreLocation storeLocation;

    public Sale() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public int getId() {
        return id;
    }
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Product getProduct() {
        return product;
    }

}
