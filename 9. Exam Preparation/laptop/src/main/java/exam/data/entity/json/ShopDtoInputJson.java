package exam.data.entity.json;

import com.google.gson.annotations.Expose;

public class ShopDtoInputJson {


    @Expose
    private String name;


    public ShopDtoInputJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
