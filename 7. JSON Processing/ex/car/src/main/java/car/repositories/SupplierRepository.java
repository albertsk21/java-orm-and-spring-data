package car.repositories;


import car.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {



    @Query("FROM Supplier WHERE id = ?1 ")
    Supplier findSupplierById(Long id);


    @Query("SELECT id FROM Supplier WHERE isImporter = true")
    List<Long> getAllIdWhereIsImporter();


    @Query("SELECT s FROM Supplier AS s " +
           "JOIN s.parts AS p " +
           "WHERE s.name LIKE '%Inc%'")
    Supplier[] findSupplierWhoImportInCountry();

}
