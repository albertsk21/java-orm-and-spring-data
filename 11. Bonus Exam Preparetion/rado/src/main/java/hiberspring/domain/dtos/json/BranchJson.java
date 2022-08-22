package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

public class BranchJson {

    @Expose
    private String name;
    @Expose
    private String town;

    public BranchJson() {
    }

    public void setTown(String town) {
        this.town = town;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }
    public String getName() {
        return name;
    }
}
