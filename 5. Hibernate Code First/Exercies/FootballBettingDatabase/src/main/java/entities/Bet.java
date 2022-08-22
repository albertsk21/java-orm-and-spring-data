package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bet_money")
    private double betMoney;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "bet", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BetGame> betGames;




    public Bet() {
    }

    public Bet(double betMoney) {
        this.setBetMoney(betMoney);
        this.setDate();
        this.betGames = new LinkedHashSet<>();
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }
    private void setBetMoney(double betMoney) {
        this.betMoney = betMoney;
    }
    private void setDate() {
        this.date = new Date(System.currentTimeMillis());
    }
    public void  addBetGame(BetGame betGame){
        this.betGames.add(betGame);
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public double getBetMoney() {
        return betMoney;
    }
    public Date getDate() {
        return date;
    }
    public User getUser() {
        return user;
    }
}
