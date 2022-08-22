package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "students")
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
    @Column(name = "average_grade")
    private double averageGrade;
    private boolean attendance;
    @ManyToMany
    private Set<Course> courses;


    public Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, boolean attendance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }
}
