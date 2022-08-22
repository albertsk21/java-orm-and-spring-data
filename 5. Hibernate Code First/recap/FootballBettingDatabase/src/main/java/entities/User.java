package entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity @Table(name = "users")
public class User {

    @Id
    private int id;

    private String username;
    private String password;
    private String email;
    @Column(name = "full_name")
    private String fullName;
    private BigDecimal balance;
    @OneToMany(mappedBy = "user")
    private Set<Bet> bets;

    public User() {
    }

    public User(String username, String password, String email, String fullName, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
