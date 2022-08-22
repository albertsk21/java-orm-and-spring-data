package entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Player getPlayer() {
        return player;
    }


    public Game getGame() {
        return game;
    }
}
