package entities;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_name")
    private String name;

    @ManyToMany(mappedBy = "studentList")
    private List<Laptop> laptops;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
        this.laptops = new ArrayList<>();

    }


    public boolean addLaptop(Laptop laptop){
        for (Laptop insideLaptop : this.laptops) {
            if(insideLaptop.getId() == laptop.getId()){
                return false;
            }
        }
        this.laptops.add(laptop);
        return true;
    }

    public void setName(String name) {
        if(name.trim().isEmpty()){
            throw new IllegalArgumentException("the name of model cannot be null or empty");
        }
        this.name = name;
    }





    public Collection<Laptop> getLaptops() {
        return Collections.unmodifiableCollection(this.laptops);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }


}
