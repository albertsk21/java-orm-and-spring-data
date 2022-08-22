package hospital.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gp")
public class GP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "gpDiagnose", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Diagnose> diagnoses;
    @OneToMany(mappedBy = "gpMedicament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Set<Medicament> drugs;
    @OneToMany(mappedBy = "gpVisitation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Set<Visitation> visitations;

    //    --------------------------------CONSTRUCTOR------------------------------------------
    public GP() {}
    //    ----------------------------------SETTERS--------------------------------------------
    public void setId(int id) {
        this.id = id;
    }
    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }
    public void setDrugs(Set<Medicament> drugs) {
        this.drugs = drugs;
    }
    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }


    //    ----------------------------------GETTERS--------------------------------------------

    public int getId() {
        return id;
    }
    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }
    public Set<Medicament> getDrugs() {
        return drugs;
    }
    public Set<Visitation> getVisitations() {
        return visitations;
    }
}
