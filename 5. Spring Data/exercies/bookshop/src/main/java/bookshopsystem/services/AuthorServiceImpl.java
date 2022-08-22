package bookshopsystem.services;

import bookshopsystem.entities.Author;
import bookshopsystem.repositories.AuthorRepository;
import bookshopsystem.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author findAuthorById(int id) {
        return this.authorRepository.getById(Long.parseLong(String.valueOf(id)));
    }

    @Override
    public List<Author> getAllAuthors() {
         return this.authorRepository.findAll();
    }
}
