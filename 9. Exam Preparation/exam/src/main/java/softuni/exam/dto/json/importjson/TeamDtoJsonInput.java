package softuni.exam.dto.json.importjson;

import com.google.gson.annotations.Expose;
import softuni.exam.modelDB.Picture;
import softuni.exam.modelDB.Player;

import java.util.LinkedHashSet;
import java.util.Set;

public class TeamDtoJsonInput {


    private Long id;
    @Expose
    private String name;
    @Expose
    private Picture picture;
    @Expose
    private Set<Player> players = new LinkedHashSet<>();


    public TeamDtoJsonInput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
