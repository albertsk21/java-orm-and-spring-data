package softuni.exam.dto.xml.importxml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PicturesRootXmlInput {

    @XmlElement(name = "picture")
    private List<PictureDtoXmlInput> pictures = new ArrayList<>();

    public PicturesRootXmlInput() {
    }

    public List<PictureDtoXmlInput> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDtoXmlInput> pictures) {
        this.pictures = pictures;
    }
}
