package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "competition_type_id")
    private CompetitionType competitionType;
    @OneToMany(mappedBy = "competition")
    private Set<Game> games;

    public CompetitionType getCompetitionType() {
        return competitionType;
    }
}
