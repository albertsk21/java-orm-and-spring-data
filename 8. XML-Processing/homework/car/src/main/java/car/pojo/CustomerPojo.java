package car.pojo;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;

public class CustomerPojo {

    @Expose
    private String fullName;
    @Expose
    private int boughtCars;
    @Expose
    private BigDecimal spentMoney;

    public CustomerPojo() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
