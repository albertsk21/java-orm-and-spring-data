package productShop.dto.xml.output.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductRootXml {

    @XmlElement(name = "product")
    private List<ProductXmlDto> products =  new ArrayList<>();

    public SoldProductRootXml() {
    }

    public List<ProductXmlDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlDto> products) {
        this.products = products;
    }
}
