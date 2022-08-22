package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String positionDescription;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;


}
