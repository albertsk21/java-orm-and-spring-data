package com.example.demo.services.implemenation;


import com.example.demo.entities.entitydatabases.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findByFirstName(String firstName) {
        return  this.employeeRepository.findByFirstName(firstName);
    }
}
