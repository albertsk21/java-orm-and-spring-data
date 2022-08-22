package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    private String id;
    private String name;
    @ManyToMany
    private Set<Continent> continents;
    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
