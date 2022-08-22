package productShop.dto.xml.output.query2;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXmlOutput {


    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "sold-products")
    private SoldProductRootXml soldProductRootXml;


    public UserXmlOutput() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SoldProductRootXml getSoldProductRootXml() {
        return soldProductRootXml;
    }

    public void setSoldProductRootXml(SoldProductRootXml soldProductRootXml) {
        this.soldProductRootXml = soldProductRootXml;
    }
}
