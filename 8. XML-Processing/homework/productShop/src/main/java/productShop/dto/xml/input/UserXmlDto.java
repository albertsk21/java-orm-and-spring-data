package productShop.dto.xml.input;


import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXmlDto implements Serializable {


    @XmlAttribute(name = "firstName")
    private String firstName;

    @XmlAttribute(name = "lastName")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;


    public UserXmlDto() {
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Integer getAge() {
        return age;
    }
}
