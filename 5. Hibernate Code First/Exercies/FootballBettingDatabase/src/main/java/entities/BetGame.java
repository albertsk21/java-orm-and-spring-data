package entities;


import javax.persistence.*;

@Entity
@Table(name = "bet_games")
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




    public BetGame(Game game, ResultPrediction resultPrediction) {
        this.game = game;
        this.resultPrediction = resultPrediction;
    }


    public BetGame() {
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }

    public int getId() {
        return id;
    }
    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }
    public Game getGame() {
        return game;
    }
    public Bet getBet() {
        return bet;
    }

}
