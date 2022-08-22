package car.pojo;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartPojo {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;


    public PartPojo() {
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
}