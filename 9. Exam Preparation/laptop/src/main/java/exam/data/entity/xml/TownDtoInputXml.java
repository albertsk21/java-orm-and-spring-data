package exam.data.entity.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownDtoInputXml {
    @XmlElement
    private String name;
    @XmlElement
    private int population;
    @XmlElement(name = "travel-guide")
    private String travelGuider;


    public TownDtoInputXml() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setTravelGuider(String travelGuider) {
        this.travelGuider = travelGuider;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuider() {
        return travelGuider;
    }
}
