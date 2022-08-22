package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String logo;
    @Column(length = 3)
    private String initials;
    @OneToOne
    @JoinColumn(name = "primary_kit_color")
    private Color primaryKitColor;
    @OneToOne
    @JoinColumn(name = "secondary_kit_color_id")
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;
    private double balance;


    @OneToMany(mappedBy = "teamPlayers", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Player> players;

    public Team() {}

    public Team(int id, String name, String logo, String initials, Color primaryKitColor, Color secondaryKitColor, Town town, double balance) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.primaryKitColor = primaryKitColor;
        this.secondaryKitColor = secondaryKitColor;
        this.town = town;
        this.balance = balance;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }
    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }
    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }
    public void setTown(Town town) {
        this.town = town;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }


    public int getId() {
        return id;
    }
    public String getLogo() {
        return logo;
    }
    public String getName() {
        return name;
    }
    public String getInitials() {
        return initials;
    }
    public double getBalance() {
        return balance;
    }
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
