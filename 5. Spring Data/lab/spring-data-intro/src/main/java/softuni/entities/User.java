package softuni.entities;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String username;
    private int age;
    @OneToMany(mappedBy = "user")
    private Set<Account>accounts;



    public User() {
    }


    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.initialize();
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void initialize() {
        this.accounts = new LinkedHashSet<>();
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public String getUsername() {
        return username;
    }
    public int getAge() {
        return age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
