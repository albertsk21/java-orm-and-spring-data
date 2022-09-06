package softuni.workshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    private String name;
    private List<Project> projects;


    public Company() {
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "company")
    public List<Project> getProjects() {
        return projects;
    }

    public Company setProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }
}
