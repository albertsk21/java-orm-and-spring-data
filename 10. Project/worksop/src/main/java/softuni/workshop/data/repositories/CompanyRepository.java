package softuni.workshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.workshop.data.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {



    @Query("FROM Company WHERE name = ?1")
    Company findCompanyByName(String name);
}
