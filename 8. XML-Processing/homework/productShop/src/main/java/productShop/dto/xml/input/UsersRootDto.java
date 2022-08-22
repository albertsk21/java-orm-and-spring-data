package productShop.dto.xml.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersRootDto {



    @XmlElement(name = "user")
    private List<UserXmlDto> users;


    public UsersRootDto() {
    }

    public List<UserXmlDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlDto> users) {
        this.users = users;
    }
}
