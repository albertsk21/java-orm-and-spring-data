package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootXml {


    @XmlElement(name = "employee")
    private List<EmployeeXml> employees;

    public EmployeeRootXml() {
    }

    public List<EmployeeXml> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeXml> employees) {
        this.employees = employees;
    }
}
