package softuni.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String password;
    @Column(name = "confirm_password")
    private String confirmPassword;
    private LocalDate created;
    private LocalDate modified;



    public User() {
    }




    public User(String username,String fullName, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
    public void modifiedDate() {
        this.modified = LocalDate.now();
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public LocalDate getCreated() {
        return created;
    }
    public LocalDate getModified() {
        return modified;
    }
}
