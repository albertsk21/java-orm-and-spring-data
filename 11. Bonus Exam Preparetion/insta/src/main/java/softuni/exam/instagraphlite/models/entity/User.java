package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "profile_pictures_id")
    private Picture profilePictures;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<Post> posts = new LinkedHashSet<>();


    public User() {}

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setProfilePictures(Picture profilePictures) {
        this.profilePictures = profilePictures;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Set<Post> getPosts() {
        return posts;
    }
    public Set<Post> getPost() {
        return posts;
    }
    public Picture getProfilePictures() {
        return profilePictures;
    }


}
