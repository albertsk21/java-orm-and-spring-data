package car.dto.xml.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersRootXmlInput {

    @XmlElement(name = "customer")
    private List<CustomerXmlInput> customerXmlInputs;

    public CustomersRootXmlInput() {
    }

    public List<CustomerXmlInput> getCustomerXmlInputs() {
        return customerXmlInputs;
    }

    public void setCustomerXmlInputs(List<CustomerXmlInput> customerXmlInputs) {
        this.customerXmlInputs = customerXmlInputs;
    }
}
