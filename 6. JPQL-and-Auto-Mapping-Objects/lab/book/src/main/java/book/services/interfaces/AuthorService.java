package book.services.interfaces;

import book.entities.Author;

public interface AuthorService {

        void saveAuthor(Author author);


        Author findById(Long id);


        int sizeAuthors();

        void countAllBookWhichIsMinor();

        void findAuthorsEndWith(String end);


}
