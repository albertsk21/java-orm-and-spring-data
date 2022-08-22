package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXml {



    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private String position;

    @XmlElement
    private String card;

    @XmlElement
    private String branch;


    public EmployeeXml() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPosition() {
        return position;
    }
    public String getCard() {
        return card;
    }
    public String getBranch() {
        return branch;
    }
}
