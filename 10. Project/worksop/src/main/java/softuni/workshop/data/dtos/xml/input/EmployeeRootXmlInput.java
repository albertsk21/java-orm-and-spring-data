package softuni.workshop.data.dtos.xml.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootXmlInput {

    @XmlElement(name = "employee")
    private List<EmployeeDtoXmlInput> employees;


    public EmployeeRootXmlInput() {
    }

    public List<EmployeeDtoXmlInput> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDtoXmlInput> employees) {
        this.employees = employees;
    }
}
