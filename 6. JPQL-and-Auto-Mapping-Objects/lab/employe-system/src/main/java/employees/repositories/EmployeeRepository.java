package employees.repositories;

import employees.database.Employee;
import employees.services.interfaces.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {



    @Query("FROM Employee AS e WHERE e.id = ?1")
    Employee findEmployeeById(Long id);


    
}
