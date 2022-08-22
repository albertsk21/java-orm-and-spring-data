package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @Column(name = "credit_card_number")
    String creditCardNumber;
    @OneToMany(mappedBy = "customer")
    Set<Sale> sales;

    public Customer() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
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

    public String getEmail() {
        return email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
