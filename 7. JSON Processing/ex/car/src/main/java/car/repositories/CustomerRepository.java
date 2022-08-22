package car.repositories;

import car.dto.CustomerDto;
import car.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {



    @Query("FROM Customer ORDER BY birthDate ASC")
    Customer[] getAllCustomerByBirthdayAscOrder();

    @Query("FROM Customer WHERE id = ?1 ")
    Customer findCustomerById(Long id);
    @Query("SELECT id FROM Customer")
    List<Long> getAllId();




}
