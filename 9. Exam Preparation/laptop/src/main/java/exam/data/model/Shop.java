package exam.data.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal income;
    private String address;
    @Column(name = "employee_count")
    private int employeeCount;
    @Column(name = "shop_area")
    private int shopArea;
    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        if(!(name.length() >= 4)){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
    public void setIncome(BigDecimal income) {
        if(!(income.doubleValue() >= 20000)){
            throw new IllegalArgumentException();
        }
        this.income = income;
    }
    public void setAddress(String address) {
        if(!(address.length() >= 4)){
            throw new IllegalArgumentException();
        }
        this.address = address;
    }
    public void setEmployeeCount(int employeeCount) {
        if(!(employeeCount >= 1 && employeeCount <= 50)){
                throw new IllegalArgumentException();
        }
        this.employeeCount = employeeCount;
    }
    public void setShopArea(int shopArea) {

        if (!(shopArea >= 150)){
            throw new IllegalArgumentException();
        }

        this.shopArea = shopArea;
    }
    public void setTown(Town town) {




        this.town = town;
    }



    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getIncome() {
        return income;
    }
    public String getAddress() {
        return address;
    }
    public int getEmployeeCount() {
        return employeeCount;
    }
    public int getShopArea() {
        return shopArea;
    }
    public Town getTown() {
        return town;
    }



}
