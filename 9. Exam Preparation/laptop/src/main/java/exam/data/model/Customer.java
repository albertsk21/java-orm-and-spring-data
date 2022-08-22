package exam.data.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "registered_on")
    private LocalDate registeredOn;
    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    public Customer() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        if (!(email.contains("@") && email.contains("."))){
            throw new IllegalArgumentException();
        }
        this.email = email;
    }

    public void setFirstName(String firstName) {

        if(!(firstName.length() >= 2 )){
            throw new IllegalArgumentException();
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        if(!(lastName.length() >= 2 )){
            throw new IllegalArgumentException();
        }

        this.lastName = lastName;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public Town getTown() {
        return town;
    }
}
