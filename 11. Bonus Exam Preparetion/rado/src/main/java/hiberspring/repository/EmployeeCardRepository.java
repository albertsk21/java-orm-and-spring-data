package hiberspring.repository;

import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard,Long> {

    @Query("FROM EmployeeCard WHERE number = ?1")
    EmployeeCard getCardByNumber(String number);

}
