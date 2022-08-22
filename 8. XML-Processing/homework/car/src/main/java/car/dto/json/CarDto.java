package car.dto.json;

import com.google.gson.annotations.Expose;


import java.util.LinkedHashSet;
import java.util.Set;

public class CarDto {



    private Long id;
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;
    private Set<PartDto> parts = new LinkedHashSet<>();
    private Set<SaleDto> sales = new LinkedHashSet<>();


    public CarDto() {

    }


    public void setId(Long id) {
        this.id = id;
    }
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

    public Set<SaleDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDto> sales) {
        this.sales = sales;
    }

    public Long getId() {
        return id;
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
    public Set<PartDto> getParts() {
        return parts;
    }

}
