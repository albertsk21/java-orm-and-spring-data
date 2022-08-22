package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;


    public Post() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Picture getPicture() {
        return picture;
    }
    public User getUser() {
        return user;
    }
    public Long getId() {
        return id;
    }
    public String getCaption() {
        return caption;
    }
}
