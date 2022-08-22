package car.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate birthDate;
    private String name;
    @Column(name = "is_young_driver")
    private boolean isYoungDriver;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Sale> sales =  new LinkedHashSet<>();

    public Customer() {
    }
    public Customer(LocalDate birthDate, String name, boolean isYoungDriver) {
        this.birthDate = birthDate;
        this.name = name;
        this.isYoungDriver = isYoungDriver;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }




    public LocalDate getBirthDate() {
        return birthDate;
    }
    public Set<Sale> getSales() {
        return sales;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getIsYoungDriver() {
        return isYoungDriver;
    }
    public boolean IsYoungDriver() {
        return isYoungDriver;
    }
}
