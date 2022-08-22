package shampoo.entities.dto;

import shampoo.entities.database.Shampoo;

import java.math.BigDecimal;
import java.util.Set;

public class IngredientDto {

    private String name;
    private BigDecimal price;
    private Set<Shampoo> shampoos;

    public IngredientDto() {
    }

    public IngredientDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Shampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<Shampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
