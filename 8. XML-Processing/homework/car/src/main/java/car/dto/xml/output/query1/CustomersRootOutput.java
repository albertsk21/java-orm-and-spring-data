package car.dto.xml.output.query1;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersRootOutput {



    @XmlElement(name = "customer")
    private List<CustomerXmlOutput> customers = new ArrayList<>();


    public CustomersRootOutput() {
    }

    public List<CustomerXmlOutput> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerXmlOutput> customers) {
        this.customers = customers;
    }
}
