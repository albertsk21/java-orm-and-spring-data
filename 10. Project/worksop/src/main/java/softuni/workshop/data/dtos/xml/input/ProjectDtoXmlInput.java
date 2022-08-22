package softuni.workshop.data.dtos.xml.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDtoXmlInput {


    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement(name = "start-date")
    private String startDate;
    @XmlElement
    private BigDecimal payment;
    @XmlElement
    private CompanyDtoXmlInput company;



    public ProjectDtoXmlInput() {
    }



    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    public void setCompany(CompanyDtoXmlInput company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getStartDate() {
        return startDate;
    }
    public BigDecimal getPayment() {
        return payment;
    }
    public CompanyDtoXmlInput getCompany() {
        return company;
    }
}
