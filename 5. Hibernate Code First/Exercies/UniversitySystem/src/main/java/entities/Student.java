package entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

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
    @Column(name = "average_grade")
    private double averageGrade;
    @ManyToMany( mappedBy = "students",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Course> courses;

//    ---------------------------CONSTRUCTOR----------------------------------------

    public Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, double averageGrade) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setAverageGrade(averageGrade);

        this.courses = new LinkedHashSet<>();
    }


    //    -----------------------------SETTERS------------------------------------------

    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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
    public double getAverageGrade() {
        return averageGrade;
    }
    public Set<Course> getCourses() {
        return courses;
    }
}
