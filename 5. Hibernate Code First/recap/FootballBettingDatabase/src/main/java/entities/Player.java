package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "squad_number")
    private String squadNumber;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @Column(name = "is_injured")
    private boolean injured;
    @OneToMany(mappedBy = "player")
    private Set<PlayerStatistics> playersStatistics;


    public Team getTeam() {
        return team;
    }


    public Position getPosition() {
        return position;
    }
}
