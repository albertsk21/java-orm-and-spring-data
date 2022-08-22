package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;



    public Continent() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
