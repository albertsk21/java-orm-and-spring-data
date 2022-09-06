package softuni.workshop.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.workshop.data.entities.Company;

import java.util.Optional;

@Repository
public interface CompanyRepository  extends JpaRepository<Company,Long> {


    @Query("FROM Company c WHERE c.name =?1")
    Optional<Company> findByName(String name);
}
