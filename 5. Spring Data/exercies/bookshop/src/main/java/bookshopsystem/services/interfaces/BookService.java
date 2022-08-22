package bookshopsystem.services.interfaces;

import bookshopsystem.entities.Book;

import java.util.List;

public interface BookService {


    void saveBook(Book book);
    List<Book> getAllBooks();
    List<Book> findHigherThan2000();
    List<Book> getBooksByAuthor(String firstName, String lastName);


}
