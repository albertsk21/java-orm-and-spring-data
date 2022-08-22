package entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private double credits;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Student> students;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;



//    ---------------------------CONSTRUCTOR----------------------------------------

    public Course() {}

    public Course(String name, String description, LocalDate startDate, LocalDate endDate, double credits) {
     this.setName(name);
     this.setDescription(description);
     this.setStartDate(startDate);
     this.setEndDate(endDate);
     this.setCredits(credits);
     this.students = new LinkedHashSet<>();
    }
//    -----------------------------SETTERS------------------------------------------

    private void setId(int id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    private void setCredits(double credits) {
        this.credits = credits;
    }
    private void setStudents(Set<Student> students) {
        this.students = students;
    }

    //    -----------------------------GETTERS------------------------------------------
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public double getCredits() {
        return credits;
    }
    public Set<Student> getStudents() {
        return students;
    }
    public Teacher getTeacher() {
        return teacher;
    }
}
