package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity @Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @OneToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    @Column(name = "home_team_goals")
    private int homeTeamGoals;
    @Column(name = "away_team_goals")
    private int awayTeamGoals;
    @Column(name = "date_time")
    private Date dateTime;
    @Column(name = "home_team_bet_rate")
    private double homeTeamWinBetRate;
    @Column(name = "away_team_bet_rate")
    private double awayTeamWinBetRate;
    @Column(name = "draw_bet_rate")
    private double drawGameBetRate;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
    @OneToMany(mappedBy = "game")
    private Set<BetGame> betGames;
    @OneToMany(mappedBy = "game")
    private Set<PlayerStatistics> playersStatistics;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    public Competition getCompetition() {
        return competition;
    }

    public Round getRound() {
        return round;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }
}
