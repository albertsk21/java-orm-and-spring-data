package entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @Column(name = "credit_card_number")
    String creditCardNumber;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Sale> sales;

//    -----------------------CONSTRUCTOR----------------------------
    public Customer() {}
    public Customer(String name, String email, String creditCardNumber) {
        this.setName(name);
        this.setEmail(email);
        this.setCreditCardNumber(creditCardNumber);
    }

//    -----------------------SETTERS----------------------------------
    private void setId(int id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    private void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    //    -----------------------GETTERS----------------------------------
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
