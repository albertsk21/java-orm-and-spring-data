package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "branches")
    private Set<Product> products = new LinkedHashSet<>();
    @OneToMany(mappedBy = "branches")
    private Set<Employee> employees = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    public Town getTown() {
        return town;
    }

    public Branch() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void setTown(Town town) {
        this.town = town;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Product> getProducts() {
        return products;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }
}
