package bookshopsystem.repositories;


import bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("FROM Book WHERE YEAR(releaseDate) >= 2000")
    List<Book> findByAgeGreaterThan2000();
    @Query("FROM Book AS b "+
           "JOIN b.author AS a " +
           "WHERE a.firstName = ?1 AND a.lastName = ?2 " +
           "ORDER BY b.releaseDate DESC, b.title")
    List<Book> findBooksByAuthorOrderByReleaseDateDescAndBookTitleASC(String firstName, String lastName);


}
