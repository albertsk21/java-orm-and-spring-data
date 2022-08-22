package shampoo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shampoo.entities.database.Ingredient;

import javax.transaction.Transactional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {



    @Query("DELETE FROM Ingredient AS i "+
           "WHERE i.name = ?1")
    void deleteIngredientByName(String  name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient AS i SET i.price = i.price * 1.10")
    void increasePriceByTenPercent();


    @Transactional
    @Modifying
    @Query("DELETE FROM Ingredient AS i WHERE i = ?1")
    void deleteIngredient(Ingredient ingredient);
}
