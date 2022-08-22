package car.dto.json;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartDto {


    private Long id;
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private int quantity;
    private SupplierDto supplierDto;

    public PartDto() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
}
