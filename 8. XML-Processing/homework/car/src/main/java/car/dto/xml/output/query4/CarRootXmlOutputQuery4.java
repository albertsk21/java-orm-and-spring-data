package car.dto.xml.output.query4;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootXmlOutputQuery4 {

    @XmlElement(name = "car")
    private List<CarXmlOutputQuery4> cars =  new ArrayList<>();


    public CarRootXmlOutputQuery4() {
    }

    public List<CarXmlOutputQuery4> getCars() {
        return cars;
    }

    public void setCars(List<CarXmlOutputQuery4> cars) {
        this.cars = cars;
    }
}
