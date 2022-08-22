package entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double quantity;
    private BigDecimal price;
    @OneToMany( mappedBy = "product",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Sale> sales;
// TODO: SET <SALES >

//    -----------------------CONSTRUCTOR-----------------------------
    public Product() {}
    public Product(String name, double quantity, BigDecimal price) {
        this.setName(name);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

//    -----------------------SETTERS----------------------------------
    private void setId(int id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    private void setPrice(BigDecimal price) {
        this.price = price;
    }
    private void setSales(Set<Sale> sales) {
        this.sales = sales;
    }


//    -----------------------GETTERS----------------------------------


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
