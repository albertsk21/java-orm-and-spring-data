package exam.data.entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopDtoInputXml {


    @XmlElement
    private String address;

    @XmlElement(name = "employee-count")
    private int employeeCount;
    private BigDecimal income;

    @XmlElement
    private String name;


    @XmlElement(name = "shop-area")
    private int shopArea;

    @XmlElement
    private TownDtoInputXml town;


    public ShopDtoInputXml() {
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public void setTown(TownDtoInputXml town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public TownDtoInputXml getTown() {
        return town;
    }
}
