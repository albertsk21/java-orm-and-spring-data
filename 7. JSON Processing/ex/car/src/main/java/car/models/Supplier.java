package car.models;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_imported")
    private boolean isImporter;
    private String name;
    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Part> parts =  new LinkedHashSet<>();

    public Supplier() {
    }

    public Supplier(boolean isImporter, String name) {
        this.isImporter = isImporter;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setImporter(boolean importer) {
        isImporter = importer;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Long getId() {
        return id;
    }
    public boolean isImporter() {
        return isImporter;
    }
    public String getName() {
        return name;
    }
    public Set<Part> getParts() {
        return parts;
    }
}
