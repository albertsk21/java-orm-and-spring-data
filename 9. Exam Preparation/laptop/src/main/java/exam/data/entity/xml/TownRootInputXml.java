package exam.data.entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownRootInputXml {


    @XmlElement(name = "town")
    private List<TownDtoInputXml> towns = new ArrayList<>();

    public TownRootInputXml() {
    }

    public List<TownDtoInputXml> getTowns() {
        return towns;
    }

    public void setTowns(List<TownDtoInputXml> towns) {
        this.towns = towns;
    }
}
