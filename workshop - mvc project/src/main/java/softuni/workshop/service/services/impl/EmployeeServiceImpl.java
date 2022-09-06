package softuni.workshop.service.services.impl;

import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.EmployeeXmlDTO;
import softuni.workshop.data.dtos.EmployeesXmlDTO;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.output.GlobalOutput;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.IValidator;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final String EMPLOYEES_PATH_XML = "src/main/resources/files/xmls/employees.xml";
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private XmlParser xmlParser;
    private IValidator iValidator;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ProjectRepository projectRepository,
                               XmlParser xmlParser,
                               IValidator iValidator) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.iValidator = iValidator;
    }

    @Override
    public void importEmployees() throws JAXBException, IllegalAccessException, InstantiationException {
        EmployeesXmlDTO employeesXmlDTO = this.xmlParser.parseXml(EmployeesXmlDTO.class,EMPLOYEES_PATH_XML);

        for (EmployeeXmlDTO employeeXmlDTO : employeesXmlDTO.getEmployees()) {
            if(this.isValid(employeeXmlDTO)){
                Employee employee = this.map(employeeXmlDTO);
                this.employeeRepository.save(employee);
            }else{
                System.out.println(GlobalOutput.INCORRECT_DATA_INPUT);
            }
        }
    }

    @Override
    public boolean areImported() {
       return  this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEES_PATH_XML));
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        List<Employee> employeeList = this.employeeRepository.findEmployeesWhoAreOlderTan25();
        StringBuilder output = new StringBuilder();


        String employeeFormat = "Name: %s\n"+
                "   Age: %d\n" +
                "   Project Name %s\n";
        for (Employee employee : employeeList) {


            output.append(String.format(employeeFormat,
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getAge(),
                    employee.getProject().getName()));


        }


        return output.toString();
    }

    private boolean isValid(EmployeeXmlDTO employeeXmlDTO) throws IllegalAccessException, InstantiationException {
        boolean isNotEmpty = this.iValidator.isValid(employeeXmlDTO);
        boolean projectExist =  !this.projectRepository.findByName(employeeXmlDTO.getProject().getName()).isEmpty();
        return isNotEmpty && projectExist;
    }

    private Employee map(EmployeeXmlDTO employeeXmlDTO){
        Project project = this.projectRepository.findByName(employeeXmlDTO.getProject().getName()).get();


        return new Employee()
                .setFirstName(employeeXmlDTO.getFirstName())
                .setLastName(employeeXmlDTO.getLastName())
                .setAge(employeeXmlDTO.getAge())
                .setProject(project);
    }
}
