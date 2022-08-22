package com.example.demo.core;

import com.example.demo.entities.dto.EmployeeDto;
import com.example.demo.entities.entitydatabases.Address;
import com.example.demo.entities.entitydatabases.Employee;
import com.example.demo.services.interfaces.AddressService;
import com.example.demo.services.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.*;
import java.math.BigDecimal;
import java.util.Random;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private AddressService addressService;
    private EmployeeService employeeService;


    public ConsoleRunner(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {


        ModelMapper modelMapper = new ModelMapper();

        Employee employee = this.employeeService.findByFirstName("Zion");


        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        System.out.println(employeeDto);




    }


    public void insertAddress() throws IOException {
        String pathAddressesFile = "C:\\Intelji projects\\Java ORM and Spring Data\\6. JPQL-and-Auto-Mapping-Objects\\lab\\demo\\src\\main\\java\\com\\example\\demo\\files\\addresess.txt";
        File file =  new File(pathAddressesFile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line = "";

        while ((line =  bufferedReader.readLine()) != null){


            String city = line;
            if(!(city.trim().isEmpty())){
                Address address = new Address(line);
                this.addressService.save(address);

            }


        }


    }
    public void insertEmployees() throws IOException {
        String path = "C:\\Intelji projects\\Java ORM and Spring Data\\6. JPQL-and-Auto-Mapping-Objects\\lab\\demo\\src\\main\\java\\com\\example\\demo\\files\\employees.txt";
        File file =  new File(path);

        BufferedReader employeeReader = new BufferedReader(new FileReader(file));


        String line = "";
        while ((line = employeeReader.readLine()) != null){

            String[] data = line.split("\\s+");


            String firstName = data[0];
            String lastName = data[1];

            BigDecimal salary = new BigDecimal(data[2]);

            Random random = new Random();
            Long addressId = random.nextLong(87);
            Address address = this.addressService.findById(addressId);

            Employee employee = new Employee(firstName,lastName,salary,address);


            this.employeeService.save(employee);



        }

    }

}
