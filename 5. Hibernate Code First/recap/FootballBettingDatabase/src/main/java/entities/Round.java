package entities;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "rounds")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "round")
    private Set<Game> games;
}
