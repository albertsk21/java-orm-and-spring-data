package book.repositories;

import book.entities.AgeRestriction;
import book.entities.Book;
import book.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {




    @Query("FROM Book As b WHERE b.editionType = ?1")
    List<Book>  findBooksByEditionType(EditionType editionType);

    @Query("FROM Book As b WHERE b.ageRestriction = ?1")
    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);


    @Query("FROM Book AS b WHERE b.price < 5 OR b.price > 40")
    List<Book> findByPrice5rHigherThan40();

    @Query("FROM Book AS b WHERE YEAR(b.releaseDate) != ?1")
    List<Book> findBookAreNotReleased(int year);


    @Query("FROM Book As b WHERE b.releaseDate > ?1")
    List<Book> findBeforeDate(LocalDate localDate);



    @Query("SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > ?1")
    Integer countBookByLengthTitle(int number);


    @Query("FROM Book AS b WHERE b.title = ?1")
    Book findByTitle(String title);




    @Transactional
    @Modifying
    @Query("UPDATE Book AS b " +
           "SET b.copies = b.copies + ?1 " +
            "WHERE b.releaseDate > ?2")
    void  increaseCopiesBeforeReleaseDate( int copies , LocalDate localDate);

    @Query(value = "CALL count_all_book_from_authors(:first,:last)", nativeQuery = true)
    int getSizeOfBookFromAuthors(@Param("first") String firstName, @Param("last") String lastName);


}
