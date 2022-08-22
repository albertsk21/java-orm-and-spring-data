package employees.core;

import employees.database.Employee;
import employees.entityDto.ManagerDto;
import employees.services.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception{


        this.employeeService.printAllManager();



    }



    public void insertEmployee() throws IOException {


        String path  = "C:\\Intelji projects\\Java ORM and Spring Data\\6. JPQL-and-Auto-Mapping-Objects\\lab\\employe-system\\src\\main\\java\\employees\\files\\employeePopulate.txt";
        File file = new File(path);
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        String line = "";



        while((line = fileReader.readLine()) != null){

            String[] data = line.split(" ");

            String firstName = data[0];
            String lastName = data[1];
            BigDecimal salary = new BigDecimal(data[2]);
            LocalDate localDate = LocalDate.now();
            String address = data[4];
            Employee employee = new Employee(firstName,lastName,salary,localDate,address);

            this.employeeService.saveEmployee(employee);
        }

    }
    public void insertEmployeeWithManagers() throws IOException {


        String path  = "C:\\Intelji projects\\Java ORM and Spring Data\\6. JPQL-and-Auto-Mapping-Objects\\lab\\employe-system\\src\\main\\java\\employees\\files\\employeePopulate.txt";
        File file = new File(path);
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        String line = "";


        List<Employee> managers = this.employeeService.getAllEmployees();
        while((line = fileReader.readLine()) != null){

            String[] data = line.split(" ");

            String firstName = data[0];
            String lastName = data[1];
            BigDecimal salary = new BigDecimal(data[2]);
            LocalDate localDate = LocalDate.now();
            String address = data[4];
            Employee employee = new Employee(firstName,lastName,salary,localDate,address);


            Random random =  new Random();
            Employee manager = this.employeeService.findById(random.nextLong(managers.size()));
            employee.setManager(manager);
            this.employeeService.saveEmployee(employee);
        }

    }

}
