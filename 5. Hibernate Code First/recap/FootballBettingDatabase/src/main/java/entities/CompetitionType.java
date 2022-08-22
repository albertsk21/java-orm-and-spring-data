package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "competition_type")
public class CompetitionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String type;
    @OneToMany(mappedBy = "competitionType")
    private Set<Competition> competitions;
}
