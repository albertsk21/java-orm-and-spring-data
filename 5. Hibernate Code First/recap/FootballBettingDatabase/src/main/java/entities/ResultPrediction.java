package entities;

import javax.persistence.*;

@Entity @Table(name = "result_predictions")
public class ResultPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prediction;

}
