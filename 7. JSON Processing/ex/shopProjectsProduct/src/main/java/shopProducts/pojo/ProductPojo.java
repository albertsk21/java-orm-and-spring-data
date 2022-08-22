package shopProducts.pojo;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductPojo {


    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String seller;


    public ProductPojo() {
    }

    public ProductPojo(String name, BigDecimal price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getSeller() {
        return seller;
    }
}
