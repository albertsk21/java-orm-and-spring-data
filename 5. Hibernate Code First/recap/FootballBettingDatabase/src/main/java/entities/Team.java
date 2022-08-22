package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity @Table(name = "teams")
public class Team {

    @Id
    private String id;
    private String name;
    private String logo;
    @ManyToOne
    @JoinColumn(name = "primary_kit_color_id")
    private Color primaryKitColor;
    @ManyToOne
    @JoinColumn(name = "secondary_kit_color_id")
    private Color secondaryKitColor;
    @OneToOne
    @JoinColumn(name = "town_id")
    private Town town;
    private BigDecimal budget;
    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    public Town getTown() {
        return town;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }
}
