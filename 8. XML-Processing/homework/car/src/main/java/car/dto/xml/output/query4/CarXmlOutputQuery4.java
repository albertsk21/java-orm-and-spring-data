package car.dto.xml.output.query4;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarXmlOutputQuery4 {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private String make;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlElement(name = "parts")
    private PartRootXmlOutputQuery4 parts;


    public CarXmlOutputQuery4() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }


    public PartRootXmlOutputQuery4 getParts() {
        return parts;
    }

    public void setParts(PartRootXmlOutputQuery4 parts) {
        this.parts = parts;
    }
}
