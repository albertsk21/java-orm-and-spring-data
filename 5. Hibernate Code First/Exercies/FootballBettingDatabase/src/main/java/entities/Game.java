package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    @Column(name = "home_team_goals")
    private int homeTeamGoals;
    @Column(name = "away_team_goals")
    private int awayTeamGoals;
    @Column(name = "date_time")
    private Date dateTime;
    @Column(name = "home_team_win_bet_rate")
    private double homeTeamWinBetRate;
    @Column(name = "away_team_win_bet_rate")
    private double awayTeamWinBetRate;
    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BetGame> betGames;



    public Game(int homeTeamGoals, int awayTeamGoals, double homeTeamWinBetRate, double awayTeamWinBetRate, Round round, Competition competition) {
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.setDate();
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.round = round;
        this.competition = competition;
        this.betGames = new LinkedHashSet<>();
    }

    public Game() {
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }
    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }
    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }
    public void setRound(Round round) {
        this.round = round;
    }
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    private void setDate() {
        this.dateTime = new Date(System.currentTimeMillis());
    }
    public void addBetGame(BetGame betGame){
        this.betGames.add(betGame);
    }
    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }


    public Round getRound() {
        return round;
    }
    public int getId() {
        return id;
    }
    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }
    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public double getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }
    public double getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }
    public Competition getCompetition() {
        return competition;
    }
    public Set<BetGame> getBetGames() {
        return betGames;
    }
    public Team getAwayTeam() {
        return awayTeam;
    }
    public Team getHomeTeam() {
        return homeTeam;
    }

}
