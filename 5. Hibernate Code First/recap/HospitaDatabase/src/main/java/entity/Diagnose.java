package entity;

import javax.persistence.*;

@Entity @Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "visitation_id")
    private Visitation visitation;

    public Visitation getVisitation() {
        return visitation;
    }

    public Diagnose() {
    }

    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }
}
