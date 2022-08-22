package tools;


import entities.Planes;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int name;
    @OneToMany(mappedBy = "company")
    private Set<Planes> planes;


    //   -------------------CONSTRUCTORS---------------------------
    public Company() {}

    public Company(int name) {
        this.setName(name);
    }


    //    ------------------SETTERS--------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setPlanes(Set<Planes> planes) {
        this.planes = planes;
    }
//    ------------------GETTERS--------------------------------


    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public Set<Planes> getPlanes() {
        return planes;
    }
}
