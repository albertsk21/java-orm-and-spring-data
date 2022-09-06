package softuni.workshop.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{
    private String name;
    private String description;

    private boolean isFinished;
    private BigDecimal payment;

    private String startDate;
    private Company company;


    public Project() {
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }
    @Column(name = "is_finished")
    public boolean isFinished() {
        return isFinished;
    }

    public Project setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public Project setPayment(BigDecimal payment) {
        this.payment = payment;
        return this;
    }
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public Project setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public Project setCompany(Company company) {
        this.company = company;
        return this;
    }
}
