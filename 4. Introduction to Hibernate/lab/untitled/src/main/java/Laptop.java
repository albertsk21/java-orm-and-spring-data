import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    private int id;
    private String model;

    public Laptop(int id, String model) {
        this.id = id;
        this.model = model;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
