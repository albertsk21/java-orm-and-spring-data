package entities;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Game> games;

    public Round() {
    }

    public Round(String name) {
        this.name = name;
        this.games =  new LinkedHashSet<>();
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addGames(Game game){
        this.games.add(game);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
