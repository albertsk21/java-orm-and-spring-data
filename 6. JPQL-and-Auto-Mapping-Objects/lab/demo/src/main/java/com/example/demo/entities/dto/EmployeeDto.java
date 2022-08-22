package com.example.demo.entities.dto;

import java.math.BigDecimal;

public class EmployeeDto {

    private String firstName;
    private String lastName;

    private BigDecimal salary;

    private String addressCity;


    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, BigDecimal salary, String addressCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.addressCity = addressCity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Override
    public String toString() {

        StringBuilder output =  new StringBuilder("Employees:\n");

        output.append(String.format("firstName: %s\n",this.firstName));
        output.append(String.format("lastName: %s\n",this.lastName));
        output.append(String.format("city: %s\n",this.addressCity));
        output.append(String.format("salary: %.2f\n",this.salary));
        return output.toString();
    }
}
