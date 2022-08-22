package employees.services.interfaces;

import employees.database.Employee;
import employees.entityDto.ManagerDto;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(Employee employee);
    Employee findById(Long id);
    List<Employee> getAllEmployees();
    List<ManagerDto> getManagers();
    void printAllManager();
}
