package hospital.entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "gp_diagnose_id")
    private GP gpDiagnose;


//    --------------------------CONSTRUCTOR---------------------------------


    public Diagnose() {}

    public Diagnose(String name, String comments) {
        this.setName(name);
        this.setComments(comments);
    }


//    ----------------------------SETTERS-----------------------------------

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setComments(String comments) {
        this.comments = comments;
    }




    //    ----------------------------GETTERS-----------------------------------


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    public GP getGpDiagnose() {
        return gpDiagnose;
    }

}
