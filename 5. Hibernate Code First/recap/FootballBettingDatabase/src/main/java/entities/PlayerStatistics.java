package entities;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "player_statistics")
public class PlayerStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private String scoredGoals;
    @Column(name = "player_assists")
    private String playerAssists;
    @Column(name = "played_minutes")
    private int playedMinutes;

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}
