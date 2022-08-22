package softuni.exam.instagraphlite.models.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostXML implements Serializable {


    private String caption;
    private UserXML user;
    private PictureXML picture;

    public PostXML() {
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setUser(UserXML user) {
        this.user = user;
    }
    public void setPicture(PictureXML picture) {
        this.picture = picture;
    }

    public String getCaption() {
        return caption;
    }
    public UserXML getUser() {
        return user;
    }
    public PictureXML getPicture() {
        return picture;
    }
}
