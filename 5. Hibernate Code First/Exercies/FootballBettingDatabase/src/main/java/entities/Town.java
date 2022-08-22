package entities;

import javax.persistence.*;
import java.security.Security;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country countryTown;
    @OneToMany(mappedBy = "town")
    private Set<Team> teams;
    public Country getCountryTown() {
        return countryTown;
    }

    public Town() {
    }

    public Town(String name) {
        this.name = name;
        this.teams = new LinkedHashSet<>();
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountryTown(Country countryTown) {
        this.countryTown = countryTown;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Team> getTeams() {
        return teams;
    }
}
