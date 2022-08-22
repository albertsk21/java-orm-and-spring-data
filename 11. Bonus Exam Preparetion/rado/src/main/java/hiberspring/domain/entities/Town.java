package hiberspring.domain.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigInteger population;
    @OneToMany(mappedBy = "town")
    private Set<Branch> branches = new LinkedHashSet<>();

    public Town() {
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPopulation(BigInteger population) {
        this.population = population;
    }
    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<Branch> getBranches() {
        return branches;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigInteger getPopulation() {
        return population;
    }
}
