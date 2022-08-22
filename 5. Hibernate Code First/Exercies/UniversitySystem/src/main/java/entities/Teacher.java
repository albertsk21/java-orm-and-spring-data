package entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "salary_per_hour")
    private double salaryPerHour;
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Course> courses;

//    ---------------------------CONSTRUCTOR----------------------------------------

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setSalaryPerHour(salaryPerHour);

    }


    //    -----------------------------SETTERS------------------------------------------

    private void setId(int id) {
        this.id = id;
    }
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    private void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

//    -----------------------------GETTERS------------------------------------------

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public double getSalaryPerHour() {
        return salaryPerHour;
    }
}
