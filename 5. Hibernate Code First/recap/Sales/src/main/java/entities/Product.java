package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private double quantity;
    @OneToMany(mappedBy = "product")
    Set<Sale> sales;


    public Product() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
