package car.pojo;

import car.dto.PartDto;
import com.google.gson.annotations.Expose;

import java.util.LinkedHashSet;
import java.util.Set;


public class CarPojo {



    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;

    private Set<PartDto> parts = new LinkedHashSet<>();
    public CarPojo() {}


    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }



    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }

    public Set<PartDto> getParts() {
        return parts;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public Long getTravelledDistance() {
        return travelledDistance;
    }
}
