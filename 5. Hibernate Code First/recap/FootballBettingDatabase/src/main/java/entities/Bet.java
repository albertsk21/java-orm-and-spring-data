package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity @Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bet_money")
    private BigDecimal betMoney;
    @Column(name = "date_time")
    private Date dateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "bet")
    private Set<BetGame> betGames;


    public User getUser() {
        return user;
    }


}
