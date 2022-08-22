package productShop.dto.xml.output.query3;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesRootXml {


    @XmlElement(name = "category")
    private List<CategoryXmlDto> categories = new ArrayList<>();

    public List<CategoryXmlDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryXmlDto> categories) {
        this.categories = categories;
    }
}
