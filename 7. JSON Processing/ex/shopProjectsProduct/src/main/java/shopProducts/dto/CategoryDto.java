package shopProducts.dto;


import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryDto {


    @Expose
    private String category;
    @Expose
    private int productsCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;
    public CategoryDto() {
    }

    public CategoryDto(String category, int productsCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }
    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }



    public String getCategory() {
        return category;
    }
    public int getProductsCount() {
        return productsCount;
    }
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
