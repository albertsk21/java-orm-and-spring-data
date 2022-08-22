package car.dto.xml.output.query4;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootXmlOutputQuery4 {



    @XmlElement(name = "part")
    private List<PartXmlOutputQuery4> parts = new ArrayList<>();


    public List<PartXmlOutputQuery4> getParts() {
        return parts;
    }

    public void setParts(List<PartXmlOutputQuery4> parts) {
        this.parts = parts;
    }
}
