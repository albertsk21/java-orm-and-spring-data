package softuni.exam.instagraphlite.models.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String path;
    @Column(nullable = false)
    private BigDecimal size;

    public Picture() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    @OneToMany(mappedBy = "profilePictures",cascade = CascadeType.ALL)
    private final Set<User> users =  new LinkedHashSet<>();
    @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
    private final Set<Post> posts = new LinkedHashSet<>();
    public Long getId() {
        return id;
    }
    public String getPath() {
        return path;
    }
    public BigDecimal getSize() {
        return size;
    }
    public Set<User> getUsers() {
        return users;
    }
    public Set<Post> getPosts() {
        return posts;
    }
}
