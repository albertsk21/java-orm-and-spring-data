package entities;

import javax.persistence.*;

@Entity @Table(name = "bet_games")
public class BetGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @OneToOne
    @JoinColumn(name = "result_prediction_id")
    private ResultPrediction resultPrediction;
    @ManyToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;

    public Bet getBet() {
        return bet;
    }

    public Game getGame() {
        return game;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }
}
