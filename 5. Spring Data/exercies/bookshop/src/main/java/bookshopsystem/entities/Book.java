package bookshopsystem.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "edition_type", nullable = true)
    private String editionType;
    private BigDecimal price;
    private int copies;
    @Column(name = "release_date" , nullable = true)
    private Date releaseDate;
    @Column(name = "age_restriction")
    private String ageRestriction;
    @ManyToMany
    private Set<Category> categories;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;



    public Book() {
    }

    public Book(String title, String description, String editionType, BigDecimal price, int copies, Date releaseDate, String ageRestriction, Author author) {
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.initialize();
        this.author = author;

    }
 public Book(String title, String editionType, BigDecimal price, int copies, Date releaseDate, String ageRestriction,Author author) {
        this.title = title;
        this.description = null;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.initialize();
        this.author = author;

    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setCopies(int copies) {
        this.copies = copies;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
    public void initialize(){
        this.categories = new LinkedHashSet<>();
    }
    public void addCategory(Category category){
        this.categories.add(category);
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getEditionType() {
        return editionType;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getCopies() {
        return copies;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public String getAgeRestriction() {
        return ageRestriction;
    }
    public Author getAuthor() {
        return author;
    }
    public Set<Category> getCategories() {
        return categories;
    }
}
