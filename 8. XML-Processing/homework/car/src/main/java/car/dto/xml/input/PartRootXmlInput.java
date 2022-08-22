package car.dto.xml.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootXmlInput {


    @XmlElement(name = "part")
    private List<PartXmlDtoInput> parts;

    public PartRootXmlInput() {
    }

    public List<PartXmlDtoInput> getParts() {
        return parts;
    }

    public void setParts(List<PartXmlDtoInput> parts) {
        this.parts = parts;
    }
}
