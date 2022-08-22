package book.repositories;

import book.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {



    @Query("FROM Author WHERE id = ?1")
    Author findAuthorById(Long id);


    @Query("FROM Author AS a WHERE a.firstName LIKE ?1")
    List<Author> findAuthorsFirstNameEndWith(String end);

}
