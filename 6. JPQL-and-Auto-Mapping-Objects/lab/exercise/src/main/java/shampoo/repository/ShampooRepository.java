package shampoo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shampoo.entities.database.Shampoo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo ,Long> {

    @Query("FROM Shampoo WHERE id = ?1")
    Shampoo findShampooById(Long id);

    @Query("FROM Shampoo WHERE size = ?1")
    List<Shampoo> findBySize(int size);

    @Query(" FROM Shampoo AS s "+
            "JOIN s.label AS l "+
            "WHERE size = ?1 OR l.id = ?2 " +
            "ORDER BY s.price" )
    List<Shampoo> findBySizeAndLabel(int size,Long label);



    @Query("FROM Shampoo AS s WHERE s.price >= ?1 " +
           "ORDER BY s.price DESC")
    List<Shampoo> findShampooHigherThan(BigDecimal price);


    @Query("SELECT COUNT(s.id) FROM Shampoo AS s "+
           "WHERE s.price < ?1")
    int countShampoosLessThanGivenPrice(BigDecimal price);



    @Query("SELECT s.brand FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name = ?1 OR i.name = ?2")
    List<String> findShampoosByIngredientName(String firstIngredientName, String secondIngredientName);



}
