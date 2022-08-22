package softuni.workshop.service.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.dtos.xml.input.CompanyDtoXmlInput;
import softuni.workshop.data.dtos.xml.input.CompanyRootXmlInput;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.paths.PathsProject;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CompanyServiceImpl implements CompanyService {

    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public void importCompanies() throws JAXBException {

        CompanyRootXmlInput companiesXml = this.xmlParser.parseXml(CompanyRootXmlInput.class,PathsProject.COMPANIES_PATH_XML);

        for (CompanyDtoXmlInput companyXml :  companiesXml.getCompanies()) {
            Company companyDB = this.modelMapper.map(companyXml,Company.class);
            this.companyRepository.saveAndFlush(companyDB);
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Path.of(PathsProject.COMPANIES_PATH_XML));
    }
}
