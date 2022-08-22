package car.dto.xml.output.query6;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesRootXmlOutputQuery6 {

    @XmlElement(name = "sale")
    private List<SaleXmlOutputQuery6> sales = new ArrayList<>();


    public SalesRootXmlOutputQuery6() {
    }

    public List<SaleXmlOutputQuery6> getSales() {
        return sales;
    }

    public void setSales(List<SaleXmlOutputQuery6> sales) {
        this.sales = sales;
    }
}
