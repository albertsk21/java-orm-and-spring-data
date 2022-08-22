package entities;


import javax.persistence.*;

@Entity
@Table(name = "billing_details")
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @ManyToOne
    private User user;


    public User getUser() {
        return user;
    }


    public BillingDetail() {
    }

    public BillingDetail(String number) {
        this.number = number;
    }







    public void setNumber(String number) {
        this.number = number;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    public int getId() {
        return id;
    }

}
