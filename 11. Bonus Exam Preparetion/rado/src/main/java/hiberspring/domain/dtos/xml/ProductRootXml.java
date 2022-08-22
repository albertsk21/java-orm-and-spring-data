package hiberspring.domain.dtos.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootXml {


    @XmlElement(name = "product")
    private List<ProductXml> products;

    public ProductRootXml() {
    }

    public List<ProductXml> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXml> products) {
        this.products = products;
    }
}
