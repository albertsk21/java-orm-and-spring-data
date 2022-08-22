package exam.data.entity.json;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class CustomerDtoInputJson {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String registeredOn;
    @Expose
    private TownDtoInputJson town;

    public CustomerDtoInputJson() {
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public void setTown(TownDtoInputJson town) {
        this.town = town;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public TownDtoInputJson getTown() {
        return town;
    }
}
