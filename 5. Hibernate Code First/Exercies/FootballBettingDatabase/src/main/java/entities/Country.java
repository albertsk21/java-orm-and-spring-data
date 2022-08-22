package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    private String id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Continent> continents;

    @OneToMany(mappedBy = "countryTown",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Town> towns;

    public Country() {}

    public Country(String name) {
        this.name = name;
        this.continents = new LinkedHashSet<>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public Set<Continent> getContinents() {
        return continents;
    }
}
