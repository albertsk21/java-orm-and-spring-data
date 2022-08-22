package entity;

import javax.persistence.*;

@Entity @Table(name = "drugs")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "visitation_id")
    private Visitation visitation;

    public Visitation getVisitation() {
        return visitation;
    }

    public Medicament(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
