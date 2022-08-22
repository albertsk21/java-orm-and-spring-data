package employees.entityDto;

import employees.database.Employee;

import java.util.Set;

public class ManagerDto {

    private String firstName;
    private String lastName;
    private Set<Employee> employees;


    public ManagerDto() {
    }

    public ManagerDto(String firstName, String lastName, Set<Employee> employees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employees = employees;
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
