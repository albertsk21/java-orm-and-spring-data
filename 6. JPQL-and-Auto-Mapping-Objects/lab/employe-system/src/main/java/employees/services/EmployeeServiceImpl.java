package employees.services;

import employees.database.Employee;
import employees.entityDto.EmployeeDto;
import employees.entityDto.ManagerDto;
import employees.repositories.EmployeeRepository;
import employees.services.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<ManagerDto> getManagers() {
        List<ManagerDto> managerDtoList = new ArrayList<>();
        ModelMapper modelMapper =  new ModelMapper();

        for (int i = 1; i <=7 ; i++) {
            Employee employee = this.findById(Long.parseLong(String.valueOf(i)));
            ManagerDto managerDto = modelMapper.map(employee,ManagerDto.class);
            managerDtoList.add(managerDto);
        }

        return managerDtoList;
    }

    @Override
    public void printAllManager(){

        for (ManagerDto managerDto : this.getManagers()) {

            System.out.printf("%s %s | Employees: %d %n",managerDto.getFirstName(),managerDto.getLastName(),managerDto.getEmployees().size());
            for (EmployeeDto employeeDto : this.getEmployeeDtoList(managerDto)) {

                System.out.printf("%s %s %.2f%n",employeeDto.getFirstName(),employeeDto.getLastName(),employeeDto.getSalary());

            }
            System.out.println();

        }


    }


    private List<EmployeeDto> getEmployeeDtoList(ManagerDto managerDto){
        ModelMapper  modelMapper =  new ModelMapper();
        Set<Employee> employees = managerDto.getEmployees();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }

}
