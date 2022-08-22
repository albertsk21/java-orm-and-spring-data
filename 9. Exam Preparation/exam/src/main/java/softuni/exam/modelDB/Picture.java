package softuni.exam.modelDB;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String url;
    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
    private Set<Team> teams = new LinkedHashSet<>();
    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
    private Set<Player> players = new LinkedHashSet<>();

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
