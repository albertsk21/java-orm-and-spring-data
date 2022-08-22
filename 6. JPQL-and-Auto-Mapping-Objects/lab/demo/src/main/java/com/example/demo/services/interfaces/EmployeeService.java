package com.example.demo.services.interfaces;


import com.example.demo.entities.entitydatabases.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);
    List<Employee> getAll();
    Employee findByFirstName(String firstName);

}
