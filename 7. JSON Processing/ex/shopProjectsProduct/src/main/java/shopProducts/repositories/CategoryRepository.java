package shopProducts.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shopProducts.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("FROM Category WHERE id = ?1")
    Category findCategoryById(Long id);



    @Query("SELECT COUNT(p.id), c.name, AVG(p.price), SUM(p.price) FROM Category AS c " +
           "JOIN c.products AS p " +
           "GROUP BY c.id ")
    List<Object[]> getAllCategories();

}
