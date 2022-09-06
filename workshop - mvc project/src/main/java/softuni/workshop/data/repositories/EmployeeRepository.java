package softuni.workshop.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.workshop.data.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("FROM Employee u WHERE u.age > 25 " +
           "ORDER BY u.firstName ASC ")
    List<Employee> findEmployeesWhoAreOlderTan25();

}
