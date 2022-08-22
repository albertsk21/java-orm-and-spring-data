package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "competition_type")
public class CompetitionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @OneToMany(mappedBy = "competitionType", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Competition> competitions;


    public CompetitionType(String type) {
        this.type = type;
        this.competitions = new LinkedHashSet<>();
    }


    public void addCompetition(Competition competition){
        this.competitions.add(competition);
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public Set<Competition> getCompetitions() {
        return competitions;
    }
}
