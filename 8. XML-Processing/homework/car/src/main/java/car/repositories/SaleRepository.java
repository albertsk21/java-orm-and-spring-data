package car.repositories;

import car.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale,Long> {


    @Query("FROM Sale")
    Sale[] getAllSales();

}
