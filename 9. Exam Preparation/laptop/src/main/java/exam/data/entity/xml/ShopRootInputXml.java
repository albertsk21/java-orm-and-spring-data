package exam.data.entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopRootInputXml {


    @XmlElement(name = "shop")
    private List<ShopDtoInputXml> shops;

    public ShopRootInputXml() {
    }

    public List<ShopDtoInputXml> getShops() {
        return shops;
    }

    public void setShops(List<ShopDtoInputXml> shops) {
        this.shops = shops;
    }
}
