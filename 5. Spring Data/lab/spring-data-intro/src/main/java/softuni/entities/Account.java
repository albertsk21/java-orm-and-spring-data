package softuni.entities;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{


    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public Account() {
    }

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }




    public BigDecimal getBalance() {
        return balance;
    }

}
