package book.services.interfaces;

import book.entities.Book;


public interface BookService {

    void saveBook(Book book);

    void findBooksByAgeRestriction(String ageRestriction);

    void findGOLDBooksLessThan5000Copies();



    void findByPrice5rHigherThan40();

    void findBookAreNoteReleased(int year);

    void findBooksByBeforeReleasedDate(String date);

    void findBooksByTitle(String contain);

    void findBooksWithAuthor(String letters);

    void countBookByLengthTitle(int number);

    void findByTitle(String title);

    void allBooksBeforeReleasedDate(String date, int copies);

    void getSizeBooksFromAuthor(String firstName, String lastName);
}
