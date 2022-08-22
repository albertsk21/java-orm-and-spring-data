package bookshopsystem.services.interfaces;
import bookshopsystem.entities.Author;

import java.util.List;


public interface AuthorService {

    void saveAuthor(Author author);
    Author findAuthorById(int id);
    List<Author> getAllAuthors();

}
