package car.dto;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;

public class CustomerDto {

    private Long id;
    @Expose
    private String name;
    @Expose
    private LocalDate birthDate;
    @Expose
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public boolean isYoungDriver() {
        return isYoungDriver;
    }

}
