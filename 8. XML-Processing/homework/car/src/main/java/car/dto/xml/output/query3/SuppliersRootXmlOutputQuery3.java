package car.dto.xml.output.query3;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersRootXmlOutputQuery3 {



    @XmlElement(name = "supplier")
    private List<SupplierXmlOutputQuery3> suppliers =  new ArrayList<>();


    public SuppliersRootXmlOutputQuery3() {
    }

    public List<SupplierXmlOutputQuery3> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierXmlOutputQuery3> suppliers) {
        this.suppliers = suppliers;
    }
}
