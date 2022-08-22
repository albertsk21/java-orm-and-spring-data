package productShop.dto.xml.output.query2;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootXmlDto {

    @XmlElement(name = "user")
    private List<UserXmlOutput> users =  new ArrayList<>();


    public UserRootXmlDto() {
    }

    public List<UserXmlOutput> getUsers() {
        return users;
    }

    public void setUsers(List<UserXmlOutput> users) {
        this.users = users;
    }
}
