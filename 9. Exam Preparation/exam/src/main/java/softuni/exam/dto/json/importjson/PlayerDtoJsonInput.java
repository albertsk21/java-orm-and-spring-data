package softuni.exam.dto.json.importjson;

import com.google.gson.annotations.Expose;
import softuni.exam.enums.Position;
import softuni.exam.modelDB.Picture;
import softuni.exam.modelDB.Team;

import javax.persistence.*;
import java.math.BigDecimal;

public class PlayerDtoJsonInput {


    private Long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int number;
    @Expose
    private String position;
    @Expose
    private BigDecimal salary;
    @Expose
    private PictureDtoJsonInput picture;
    @Expose
    private TeamDtoJsonInput team;


    public PlayerDtoJsonInput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public PictureDtoJsonInput getPicture() {
        return picture;
    }

    public void setPicture(PictureDtoJsonInput picture) {
        this.picture = picture;
    }

    public TeamDtoJsonInput getTeam() {
        return team;
    }

    public void setTeam(TeamDtoJsonInput team) {
        this.team = team;
    }
}
