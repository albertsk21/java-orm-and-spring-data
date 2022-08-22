package car.dto.xml.output.query2;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsRootXmlOutputQuery2 {


    @XmlElement(name = "car")
    private List<CarXmlOutputQuery2> cars = new ArrayList<>();


    public CarsRootXmlOutputQuery2() {
    }

    public List<CarXmlOutputQuery2> getCars() {
        return cars;
    }

    public void setCars(List<CarXmlOutputQuery2> cars) {
        this.cars = cars;
    }
}
