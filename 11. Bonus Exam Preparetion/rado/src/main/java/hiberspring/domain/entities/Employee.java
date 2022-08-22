package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    @OneToOne
    @JoinColumn(name = "card_id")
    private EmployeeCard card;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branches;

    public Employee() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setCard(EmployeeCard card) {
        this.card = card;
    }
    public void setBranches(Branch branches) {
        this.branches = branches;
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPosition() {
        return position;
    }
    public Branch getBranches() {
        return branches;
    }
    public Branch getBranch() {
        return branches;
    }
    public EmployeeCard getCard() {
        return card;
    }


}
