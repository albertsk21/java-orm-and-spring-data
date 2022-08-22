package exam.data.entity.json;

import com.google.gson.annotations.Expose;

public class TownDtoInputJson {

    @Expose
    private String name;

    public TownDtoInputJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
