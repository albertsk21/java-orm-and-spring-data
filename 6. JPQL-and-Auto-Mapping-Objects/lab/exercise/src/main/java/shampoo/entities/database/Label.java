package shampoo.entities.database;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Shampoo> shampoos;


    public Label() {
    }

    public Label(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.shampoos = new LinkedHashSet<>();
    }




    public void setTitle(String title) {
        this.title = title;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void  addShampoo(Shampoo shampoo){
        this.shampoos.add(shampoo);
    }

    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public Long getId() {
        return id;
    }

    public Set<Shampoo> getShampoos() {
        return shampoos;
    }
}
