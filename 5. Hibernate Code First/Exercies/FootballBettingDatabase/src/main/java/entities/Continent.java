package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;



    public Continent() {}
    public Continent(String name) {
        this.name = name;
        this.countries = new LinkedHashSet<>();
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Set<Country> getCountries() {
        return countries;
    }
}
