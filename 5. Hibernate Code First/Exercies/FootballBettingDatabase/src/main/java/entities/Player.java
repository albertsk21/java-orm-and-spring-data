package entities;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "squad_number")
    private int squadNumber;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamPlayers;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @Column(name = "is_injured")
    private boolean isCurrentlyInjured;



    public Player() {
    }

    public Player(String name, int squadNumber, Team teamPlayers, Position position, boolean isCurrentlyInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.teamPlayers = teamPlayers;
        this.position = position;
        this.isCurrentlyInjured = isCurrentlyInjured;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }
    public void setTeam(Team team) {
        this.teamPlayers = team;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTeamPlayers(Team teamPlayers) {
        this.teamPlayers = teamPlayers;
    }



    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getSquadNumber() {
        return squadNumber;
    }
    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }
    public Position getPosition() {
        return position;
    }
    public Team getTeam() {
        return teamPlayers;
    }




}
