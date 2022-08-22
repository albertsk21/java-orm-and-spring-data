package softuni.workshop.data.dtos.xml.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootXmlInput {


    @XmlElement(name = "company")
    private List<CompanyDtoXmlInput> companies;

    public CompanyRootXmlInput() {
    }

    public List<CompanyDtoXmlInput> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDtoXmlInput> companies) {
        this.companies = companies;
    }
}
