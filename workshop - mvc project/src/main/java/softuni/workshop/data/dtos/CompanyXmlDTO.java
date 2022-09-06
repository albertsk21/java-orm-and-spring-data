package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyXmlDTO {

    @XmlAttribute(name = "name")
    private String name;


    public CompanyXmlDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
}
