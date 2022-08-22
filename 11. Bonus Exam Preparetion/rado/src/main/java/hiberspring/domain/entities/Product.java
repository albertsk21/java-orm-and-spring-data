package hiberspring.domain.entities;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int clients;
    @ManyToOne
    @JoinColumn(name = "branches_id")
    private Branch branches;

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setClients(int clients) {
        this.clients = clients;
    }
    public void setBranches(Branch branches) {
        this.branches = branches;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getClients() {
        return clients;
    }
    public Branch getBranches() {
        return branches;
    }
}
