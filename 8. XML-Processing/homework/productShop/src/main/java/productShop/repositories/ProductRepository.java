package productShop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productShop.models.Product;


@Repository
public interface ProductRepository extends JpaRepository <Product,Long> {



    @Query("FROM Product WHERE price BETWEEN 500 AND 1000")
    Product[] getAllProductsWithOfPrice();


}
