package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "competition_type_id")
    private CompetitionType competitionType;
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> games;




    public Competition(String name, CompetitionType competitionType) {
        this.name = name;
        this.competitionType = competitionType;
        this.games = new LinkedHashSet<>();
    }



    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
    public void addGames(Game game){
        this.games.add(game);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public CompetitionType getCompetitionType() {
        return competitionType;
    }

}
