package entities;

import javax.persistence.*;


@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prediction;
    @OneToOne(mappedBy = "resultPrediction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BetGame betGame;

    public BetGame getBetGame() {
        return betGame;
    }

    public ResultPrediction() {
    }

    public ResultPrediction(String prediction) {
        this.prediction = prediction;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public int getId() {
        return id;
    }

    public String getPrediction() {
        return prediction;
    }
}
