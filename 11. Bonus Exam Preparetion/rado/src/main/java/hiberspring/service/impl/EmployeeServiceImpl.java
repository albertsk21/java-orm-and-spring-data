package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.xml.EmployeeRootXml;
import hiberspring.domain.dtos.xml.EmployeeXml;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.filepaths.FilesPaths;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private BranchRepository branchRepository;
    private EmployeeCardRepository employeeCardRepository;
    private EmployeeRepository employeeRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private FileUtil fileUtil;

    public EmployeeServiceImpl(BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository, EmployeeRepository employeeRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.employeeRepository = employeeRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return (int) this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(FilesPaths.EMPLOYEES_XML);
    }

    @Override
    public String importEmployees() throws JAXBException {


        EmployeeRootXml employeesXml = this.xmlParser.parseXml(EmployeeRootXml.class,FilesPaths.EMPLOYEES_XML);
        StringBuilder output = new StringBuilder();

        for (EmployeeXml employeeXml : employeesXml.getEmployees()) {
            try {
                this.detectIsFieldNull(employeeXml);
                Branch currentBranch = this.getBranchByName(employeeXml.getBranch());
                EmployeeCard currentCard = this.getCardByNumber(employeeXml.getCard());
                Employee employeeToDB = this.modelMapper.map(employeeXml,Employee.class);
                employeeToDB.setBranches(currentBranch);
                employeeToDB.setCard(currentCard);

                this.employeeRepository.saveAndFlush(employeeToDB);
                String fullName = String.format("%s %s",employeeToDB.getFirstName(),employeeToDB.getLastName());
                output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,Employee.class.getSimpleName(),fullName));
            }catch (IllegalArgumentException | InstantiationException | IllegalAccessException e){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
            }
        }
        return output.toString();
    }

    @Override
    public String exportProductiveEmployees() {

        StringBuilder output = new StringBuilder();

        List<Object[]> employees = this.employeeRepository.getAllEmployees();


        for (Object[] objects : employees) {
            output.append(String.format("Name: %s%n",objects[0]));
            output.append(String.format("Position: %s%n",objects[1]));
            output.append(String.format("Card Number: %s%n",objects[2]));
            output.append(String.format("-------------------------%n"));

        }
        return output.toString();
    }


    public void detectIsFieldNull(EmployeeXml employee) throws InstantiationException, IllegalAccessException {
        boolean check = this.validationUtil.isValid(employee);
        if(!check){
            throw new IllegalArgumentException();
        }
    }
    public Branch getBranchByName(String name){
        Branch branch = this.branchRepository.getBranchByName(name);
        if(branch == null){
            throw new IllegalArgumentException();
        }
        return  branch;
    }
    public EmployeeCard getCardByNumber(String number){
        EmployeeCard card = this.employeeCardRepository.getCardByNumber(number);
        if(card == null){
            throw new IllegalArgumentException();
        }

        return card;
    }
}
