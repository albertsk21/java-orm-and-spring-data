package car.pojo;

import car.dto.CarDto;
import car.dto.CustomerDto;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;


public class SalePojo {




    @Expose
    private CarPojo car;
    @Expose
    private String customerName;
    @Expose
    private Float discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SalePojo() {
    }


    public void setCar(CarPojo car) {
        this.car = car;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarPojo getCar() {
        return car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Float getDiscount() {
        return discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }
}
