package exam.data.entity.json;

import com.google.gson.annotations.Expose;
import exam.enums.WarrantyType;

import java.math.BigDecimal;

public class LaptopDtoInputJson {

    @Expose
    private String macAddress;
    @Expose
    private double cpuSpeed;
    @Expose
    private int ram;
    @Expose
    private int storage;
    @Expose
    private String description;
    @Expose
    private BigDecimal price;
    @Expose
    private String warrantyType;
    @Expose
    private ShopDtoInputJson shop;

    public LaptopDtoInputJson() {
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    public void setShop(ShopDtoInputJson shop) {
        this.shop = shop;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public ShopDtoInputJson getShop() {
        return shop;
    }
}
