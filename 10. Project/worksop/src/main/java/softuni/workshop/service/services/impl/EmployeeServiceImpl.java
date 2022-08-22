package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.dtos.xml.input.EmployeeDtoXmlInput;
import softuni.workshop.data.dtos.xml.input.EmployeeRootXmlInput;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.paths.PathsProject;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    public EmployeeServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, CompanyRepository companyRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void importEmployees() throws JAXBException {
        EmployeeRootXmlInput employeesXml = this.xmlParser.parseXml(EmployeeRootXmlInput.class,PathsProject.EMPLOYEES_PATH_XML);
        for (EmployeeDtoXmlInput employeeXml : employeesXml.getEmployees()) {
            Employee employee = this.convertEmployeeXmlToEmployeeDB(employeeXml);
            this.employeeRepository.saveAndFlush(employee);
        }
    }



    private Employee convertEmployeeXmlToEmployeeDB(EmployeeDtoXmlInput employeeXml){

        Employee employee = new Employee();

        employee.setFirstName(employeeXml.getFirstName());
        employee.setLastName(employeeXml.getLastName());
        employee.setAge(employeeXml.getAge());
        Project project = this.projectRepository.findProjectByName(employeeXml.getProject().getName());

        if(project == null){
            throw new IllegalArgumentException("Project not found");
        }
        employee.setProject(project);
        return employee;
    }
    @Override
    public boolean areImported() {
       return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {

        return Files.readString(Path.of(PathsProject.PROJECTS_PATH_XML));
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        //TODO export employees with age above 25
        return null;
    }
}
