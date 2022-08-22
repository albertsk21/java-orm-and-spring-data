package book.entities;

import javax.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Book> books;


    public Category() {
    }
    public Category(String name) {
        this.name = name;
        this.books = new LinkedHashSet<>();
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void initialize(){
        this.books = new LinkedHashSet<>();
    }
    public void addBook(Book book){
        this.books.add(book);
    }


    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }
    public Set<Book> getBooks() {
        return books;
    }
}
