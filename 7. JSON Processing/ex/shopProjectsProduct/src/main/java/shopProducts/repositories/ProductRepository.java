package shopProducts.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shopProducts.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    @Query("FROM Product WHERE price >= 500  and price <= 1000 ORDER BY price ASC")
    List<Product> getAllProductPriceRange500to1000();

}
