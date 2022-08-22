package exam.data.model;


import exam.enums.WarrantyType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpu_speed")
    private double cpuSpeed;
    @Column(length = 2000)
    private String description;
    @Column(name = "mac_address", unique = true)
    private String macAddress;
    private BigDecimal price;
    private int ram;
    private int storage;
    @Column(name = "warranty_type")
    private WarrantyType warrantyType;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    public Laptop() {
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setCpuSpeed(double cpuSpeed) {

        if (cpuSpeed < 0){
            throw new IllegalArgumentException();
        }

        this.cpuSpeed = cpuSpeed;
    }
    public void setDescription(String description) {

        if (!(description.length() >= 10)){
            throw new IllegalArgumentException();
        }

        this.description = description;
    }
    public void setMacAddress(String macAddress) {
        if(!(macAddress.length() > 8)){
            throw new IllegalArgumentException();
        }
        this.macAddress = macAddress;
    }
    public void setPrice(BigDecimal price) {


        if (price.doubleValue() < 0){
            throw new IllegalArgumentException();
        }

        this.price = price;
    }
    public void setRam(int ram) {
        if(!(ram >= 8 && ram <= 128)){
            throw new IllegalArgumentException();
        }

        this.ram = ram;
    }
    public void setStorage(int storage) {

        if(!(storage >= 128 && ram <= 1024)){
            throw new IllegalArgumentException();
        }

        this.storage = storage;
    }
    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }
    public void setShop(Shop shop) {
        this.shop = shop;
    }



    public Long getId() {
        return id;
    }
    public double getCpuSpeed() {
        return cpuSpeed;
    }
    public String getDescription() {
        return description;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getRam() {
        return ram;
    }
    public int getStorage() {
        return storage;
    }
    public WarrantyType getWarrantyType() {
        return warrantyType;
    }
    public Shop getShop() {
        return shop;
    }
}
