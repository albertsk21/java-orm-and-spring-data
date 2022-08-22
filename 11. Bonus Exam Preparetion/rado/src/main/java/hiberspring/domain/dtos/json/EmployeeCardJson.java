package hiberspring.domain.dtos.json;


import com.google.gson.annotations.Expose;

public class EmployeeCardJson {


    @Expose
    private String number;

    public EmployeeCardJson() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
