package shopProducts.dto;


import com.google.gson.annotations.Expose;
import shopProducts.models.Product;

import java.util.Set;

public class UserDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductDto> soldProducts;



    public UserDto() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSoldProducts(Set<ProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Set<ProductDto> getSoldProducts() {
        return soldProducts;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

}
