package car.dto.xml.output.query2;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarXmlOutputQuery2 {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private String make;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    public CarXmlOutputQuery2() {
    }


}
