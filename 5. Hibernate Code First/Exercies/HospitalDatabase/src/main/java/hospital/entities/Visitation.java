package hospital.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comments;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "gp_visitations_id")
    private GP gpVisitation;



    //    --------------------------CONSTRUCTOR---------------------------------
    public Visitation() {
    }
    public Visitation(String comments, LocalDate date) {
        this.setComments(comments);
        this.setDate(date);
    }
//    ----------------------------SETTERS--------------------------------------
    private void setId(int id) {
        this.id = id;
    }
    private void setComments(String comments) {
        this.comments = comments;
    }
    private void setDate(LocalDate date) {
        this.date = date;
    }


    //    ----------------------------GETTERS-----------------------------------
    public int getId() {
        return id;
    }
    public String getComments() {
        return comments;
    }
    public LocalDate getDate() {
        return date;
    }
    public GP getGpVisitation() {
        return gpVisitation;
    }



    public String printVisitation(){
        return String.format("%s at %s", this.getComments(), this.getDate());
    }

}
