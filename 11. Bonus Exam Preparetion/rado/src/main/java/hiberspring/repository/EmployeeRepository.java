package hiberspring.repository;


import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("SELECT CONCAT(e.firstName, ' ',  e.lastName) AS fullName, e.position, c.number  FROM Employee AS e " +
           "JOIN e.card AS c " +
            "JOIN e.branches " +
           "ORDER BY  LENGTH(e.position) ASC, fullName  ASC")
    List<Object[]> getAllEmployees();


}
