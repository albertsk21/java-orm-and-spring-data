package softuni.workshop.data.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Project> projects = new LinkedHashSet<>();


    public Company() {
    }


    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
