package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.data.dtos.xml.input.ProjectDtoXmlInput;
import softuni.workshop.data.dtos.xml.input.ProjectRootXmlInput;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.paths.PathsProject;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class ProjectServiceImpl implements ProjectService {


    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private CompanyRepository companyRepository;
    private ProjectRepository projectRepository;


    public ProjectServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, CompanyRepository companyRepository, ProjectRepository projectRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void importProjects() throws JAXBException {
        ProjectRootXmlInput projectsXml = this.xmlParser.parseXml(ProjectRootXmlInput.class, PathsProject.PROJECTS_PATH_XML);
        for (ProjectDtoXmlInput projectXml : projectsXml.getProjects()) {
            Project projectDB = this.modelMapper.map(projectXml,Project.class);
            this.projectRepository.saveAndFlush(projectDB);

        }

    }

    @Override
    public boolean areImported() {
       return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return Files.readString(Path.of(PathsProject.PROJECTS_PATH_XML));
    }

    @Override
    public String exportFinishedProjects(){
        //TODO export finished projects
        return null;
    }
}
