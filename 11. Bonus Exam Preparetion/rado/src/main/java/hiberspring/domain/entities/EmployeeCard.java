package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String number;
    public Long getId() {
        return id;
    }
    @OneToOne(mappedBy = "card")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
    public EmployeeCard() {

    }
}
