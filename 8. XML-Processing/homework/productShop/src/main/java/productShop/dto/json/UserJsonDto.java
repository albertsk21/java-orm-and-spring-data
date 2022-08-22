package productShop.dto.json;


import com.google.gson.annotations.Expose;



public class UserJsonDto {


    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;


    public UserJsonDto() {
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
}
