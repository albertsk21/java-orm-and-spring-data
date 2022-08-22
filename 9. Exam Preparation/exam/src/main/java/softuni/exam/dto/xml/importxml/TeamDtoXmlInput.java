package softuni.exam.dto.xml.importxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamDtoXmlInput {



    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "picture")
    private PictureDtoXmlInput picture;


    public TeamDtoXmlInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureDtoXmlInput getPicture() {
        return picture;
    }

    public void setPicture(PictureDtoXmlInput picture) {
        this.picture = picture;
    }
}
