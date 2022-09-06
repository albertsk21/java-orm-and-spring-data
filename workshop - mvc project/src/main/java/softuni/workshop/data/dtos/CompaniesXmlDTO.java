package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesXmlDTO {


    @XmlElement(name = "company")
    private List<CompanyXmlDTO> companies;

    public CompaniesXmlDTO() {
    }

    public List<CompanyXmlDTO> getCompanies() {
        return companies;
    }

    public CompaniesXmlDTO setCompanies(List<CompanyXmlDTO> companies) {
        this.companies = companies;
        return this;
    }
}
