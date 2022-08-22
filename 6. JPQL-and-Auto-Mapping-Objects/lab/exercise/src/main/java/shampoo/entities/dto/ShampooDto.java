package shampoo.entities.dto;


import shampoo.entities.database.Ingredient;
import shampoo.entities.database.Label;

import java.math.BigDecimal;
import java.util.Set;

public class ShampooDto {


    private String brand;
    private BigDecimal price;
    private int size;
    private Set<Ingredient> ingredients;
    private Label label;

    public ShampooDto() {
    }

    public ShampooDto(String brand, BigDecimal price, int size, Set<Ingredient> ingredients, Label label) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.ingredients = ingredients;
        this.label = label;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void setLabel(Label label) {
        this.label = label;
    }


    public BigDecimal getPrice() {
        return price;
    }
    public int getSize() {
        return size;
    }
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public Label getLabel() {
        return label;
    }
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f euro.", this.getBrand(),this.convertSizeInWord(),this.getPrice());
    }


    public String convertSizeInWord(){



        String output = "";
        switch (this.getSize()){
            case 0:
                output = "SMALLER";
                break;
            case 1:
                output = "MEDIUM";
                break;
            case 2:
                output = "LARGE";
                break;
        }

        return output;
    }





}
