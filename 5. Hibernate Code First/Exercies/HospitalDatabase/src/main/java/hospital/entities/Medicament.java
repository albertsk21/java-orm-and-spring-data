package hospital.entities;


import javax.persistence.*;

@Entity
@Table(name = "drugs")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "gp_medicament_id")
    private GP gpMedicament;


    //    --------------------------------CONSTRUCTOR------------------------------------------
    public Medicament() {
    }

    public Medicament(String name) {
        this.setName(name);
    }
//    ----------------------------------SETTERS--------------------------------------------

    public void setId(int id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
//    ----------------------------GETTERS-----------------------------------
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public GP getGpMedicament() {
        return gpMedicament;
    }

}
