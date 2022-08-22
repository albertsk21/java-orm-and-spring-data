package bookshopsystem.services;


import bookshopsystem.entities.Book;
import bookshopsystem.repositories.BookRepository;
import bookshopsystem.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findHigherThan2000() {
        return this.bookRepository.findByAgeGreaterThan2000();
    }

    @Override
    public List<Book> getBooksByAuthor(String firstName, String lastName) {
        return this.bookRepository.findBooksByAuthorOrderByReleaseDateDescAndBookTitleASC(firstName,lastName);
    }


}
