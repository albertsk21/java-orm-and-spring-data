package entity;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String email;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String picture;
    private boolean insurance;
    @OneToOne(mappedBy = "patient")
    private Visitation visitation;

    public Visitation getVisitation() {
        return visitation;
    }

    public Patient() {
    }

    public Patient(String firstName, String lastName, String address, String email, Date dateOfBirth, String picture, boolean insurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.insurance = insurance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    public boolean isInsurance() {
        return insurance;
    }
}
