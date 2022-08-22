package book.services;

import book.entities.Author;
import book.entities.Book;
import book.repositories.AuthorRepository;
import book.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        this.authorRepository.save(author);
    }
    @Override
    public Author findById(Long id){
      return this.authorRepository.findAuthorById(id);
    }
    @Override
    public int sizeAuthors(){
        return (int) this.authorRepository.count();
    }



    @Override
    public void countAllBookWhichIsMinor(){

    }

    @Override
    public void findAuthorsEndWith(String end){


        String input = "%"+ end;
        List<Author> authors =  this.authorRepository.findAuthorsFirstNameEndWith(input);
        for (Author author : authors) {

            System.out.printf("%s %s%n",author.getFirstName(),author.getLastName());

        }
    }

}
