package entities;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToOne(mappedBy = "town")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public Country getCountry() {
        return country;
    }
}
