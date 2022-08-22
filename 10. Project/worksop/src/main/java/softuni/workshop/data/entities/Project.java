package softuni.workshop.data.entities;


import javax.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "is_finished")
    private boolean isFinished;

    @Column(nullable = false)
    private BigDecimal payment;

    @Column(name = "start_date")
    private String startDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Employee> employees = new LinkedHashSet<>();


    public Project() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setFinished(boolean finished) {
        isFinished = finished;
    }
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isFinished() {
        return isFinished;
    }
    public BigDecimal getPayment() {
        return payment;
    }
    public String getStartDate() {
        return startDate;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }
    public Company getCompany() {
        return company;
    }

}
