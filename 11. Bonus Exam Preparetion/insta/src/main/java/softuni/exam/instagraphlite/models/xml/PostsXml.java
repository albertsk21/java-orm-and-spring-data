package softuni.exam.instagraphlite.models.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsXml {

    @XmlElement(name = "post")
    private List<PostXML> posts;

    public PostsXml() {
    }

    public List<PostXML> getPosts() {
        return posts;
    }

    public void setPosts(List<PostXML> posts) {
        this.posts = posts;
    }
}
