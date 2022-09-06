package softuni.workshop.service.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.CompaniesXmlDTO;
import softuni.workshop.data.dtos.CompanyXmlDTO;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.output.GlobalOutput;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.IValidator;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final String COMPANIES_PATH_XML = "src/main/resources/files/xmls/companies.xml";
    private CompanyRepository companyRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private IValidator iValidator;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              XmlParser xmlParser,
                              ModelMapper modelMapper, IValidator iValidator) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.iValidator = iValidator;
    }

    @Override
    public void importCompanies() throws JAXBException, IllegalAccessException, InstantiationException {
        CompaniesXmlDTO companiesXml = xmlParser.parseXml(CompaniesXmlDTO.class,COMPANIES_PATH_XML);


        for (CompanyXmlDTO companyXmlDTO : companiesXml.getCompanies()) {
            Company company = this.modelMapper.map(companyXmlDTO,Company.class);


            if(this.iValidator.isValid(companyXmlDTO)){
                this.companyRepository.save(company);
            }else {
                    System.out.println(GlobalOutput.INCORRECT_DATA_INPUT);
            }
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {

        return Files.readString(Path.of(COMPANIES_PATH_XML));
    }
}
