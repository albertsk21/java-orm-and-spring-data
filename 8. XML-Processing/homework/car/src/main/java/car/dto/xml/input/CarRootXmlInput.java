package car.dto.xml.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootXmlInput {

    @XmlElement(name = "car")
    private List<CarInputXml> cars;


    public CarRootXmlInput() {
    }

    public List<CarInputXml> getCars() {
        return cars;
    }

    public void setCars(List<CarInputXml> cars) {
        this.cars = cars;
    }
}
