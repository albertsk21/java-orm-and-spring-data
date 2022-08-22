package productShop.dto.xml.output.query1;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootOutput {


    @XmlElement(name = "product")
    private List<ProductXmlDtoOutput> products = new ArrayList<>();

    public ProductRootOutput() {
    }

    public List<ProductXmlDtoOutput> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlDtoOutput> products) {
        this.products = products;
    }
}
