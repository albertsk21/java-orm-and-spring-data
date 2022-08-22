package car.dto.xml.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersRootXmlInput {


    @XmlElement(name = "supplier")
    private List<SupplierXmlDtoInput> suppliers;

    public SuppliersRootXmlInput() {
    }

    public List<SupplierXmlDtoInput> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierXmlDtoInput> suppliers) {
        this.suppliers = suppliers;
    }
}
