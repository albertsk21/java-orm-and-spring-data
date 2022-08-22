package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
public class Laptop{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laptop")
    private int id;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private int year;
    @ManyToMany
    private List<Student> studentList =  new ArrayList<>();


    public Laptop(){
    }
    public Laptop(String model, int year) {
        this.setModel(model);
        this.setYear(year);
    }


    public void addStudent(Student student){
        this.studentList.add(student);
    }


    public int getId() {
        return id;
    }

    public Collection<Student> getStudent() {
        return Collections.unmodifiableCollection(this.studentList);
    }

    public void setId(int id) {
        if(id < 1){
            throw new IllegalArgumentException("the id cannot be negative number or zero");
        }
        this.id = id;
    }


    private void setModel(String model) {

        if(model.trim().isEmpty()){
            throw new IllegalArgumentException("the name of model cannot be null or empty");
        }
        this.model = model;
    }


    public void setYear(int year) {

        if(year <= 1800){
            throw new IllegalArgumentException("the year cannot be smaller than 1800");
        }

        this.year = year;
    }

    private int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

}
