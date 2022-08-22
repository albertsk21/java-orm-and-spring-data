package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity @Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String comments;
    @OneToMany(mappedBy = "visitation")
    private Set<Diagnose> diagnoses;
    @OneToMany(mappedBy = "visitation")
    private Set<Medicament> drugs;
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }
}
