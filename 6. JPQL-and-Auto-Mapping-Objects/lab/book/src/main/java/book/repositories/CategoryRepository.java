package book.repositories;

import book.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {



    @Query("FROM Category WHERE id = ?1")
    Category findCategoryById(Long id);
}
