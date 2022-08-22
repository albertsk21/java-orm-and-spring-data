package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Player> players;

    public Position() {
    }
    public Position(String name) {
        this.name = name;
        this.players = new LinkedHashSet<>();
    }



    public void addPayers(Player player){
        this.players.add(player);
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Player> getPlayers() {
        return players;
    }
}
