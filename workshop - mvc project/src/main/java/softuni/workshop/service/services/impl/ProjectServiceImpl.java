package softuni.workshop.service.services.impl;

import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.ProjectXmlDTO;
import softuni.workshop.data.dtos.ProjectsXmlDTO;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.output.GlobalOutput;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.IValidator;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    private String PROJECTS_PATH_XML = "src/main/resources/files/xmls/projects.xml";
    private ProjectRepository projectRepository;
    private CompanyRepository companyRepository;
    private XmlParser xmlParser;
    private IValidator iValidator;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              CompanyRepository companyRepository,
                              XmlParser xmlParser,
                              IValidator iValidator) {

        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.iValidator = iValidator;
    }

    @Override
    public void importProjects() throws JAXBException, IllegalAccessException, InstantiationException {
        ProjectsXmlDTO projectsXmlDTO = this.xmlParser.parseXml(ProjectsXmlDTO.class,PROJECTS_PATH_XML);


        for (ProjectXmlDTO projectXmlDTO : projectsXmlDTO.getProjects()) {
            if (this.isValid(projectXmlDTO)){
                Project project = this.map(projectXmlDTO);
                this.projectRepository.save(project);
            }else{
                System.out.println(GlobalOutput.INCORRECT_DATA_INPUT);
            }
        }

    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
      return Files.readString(Path.of(PROJECTS_PATH_XML));
    }

    @Override
    public String exportFinishedProjects(){
        List<Project> projectList = this.projectRepository.findProjectsWhichAreFinished();


        String formatProject = "Project Name: %s\n"+
                "   Description: %s\n"+
                "   Salary: %s\n";

        StringBuilder output = new StringBuilder();


        for (Project project : projectList) {

            output.append(String.format(formatProject,project.getName(),
                    project.getDescription(),
                    project.getPayment()));

        }

        return output.toString();
    }
    private boolean isValid(ProjectXmlDTO projectXmlDTO) throws IllegalAccessException, InstantiationException {
        boolean isNotEmpty = this.iValidator.isValid(projectXmlDTO);
        boolean existCompany = !(this.companyRepository.findByName(projectXmlDTO.getCompany().getName())).isEmpty();
        return isNotEmpty && existCompany;
    }
    private Project map(ProjectXmlDTO projectXmlDTO){
        Company company = this.companyRepository.findByName(projectXmlDTO.getCompany().getName()).get();

        return new Project()
                .setCompany(company)
                .setDescription(projectXmlDTO.getDescription())
                .setFinished(projectXmlDTO.isFinished())
                .setName(projectXmlDTO.getName())
                .setPayment(projectXmlDTO.getPayment())
                .setStartDate(projectXmlDTO.getStartDate());
    }
}
