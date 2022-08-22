package com.example.demo.repositories;


import com.example.demo.entities.entitydatabases.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("FROM Employee WHERE firstName = ?1")
    Employee findByFirstName(String firstName);
}
