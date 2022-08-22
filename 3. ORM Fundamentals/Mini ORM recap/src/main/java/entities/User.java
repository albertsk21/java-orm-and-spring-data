package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "age")
    private int age;

    public User(String username, String password, Date registrationDate, int age) {
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.age = age;
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
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void setAge(int age) {
        this.age = age;
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
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public int getAge() {
        return age;
    }
}
