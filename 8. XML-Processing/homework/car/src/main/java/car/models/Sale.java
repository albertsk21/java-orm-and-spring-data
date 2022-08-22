package car.models;


import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float discount;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Sale() {
    }

    public Sale(float discount) {
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }
    public Long getId() {
        return id;
    }
    public float getDiscount() {
        return discount;
    }
    public Customer getCustomer() {
        return customer;
    }

}
