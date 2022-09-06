package softuni.workshop.data.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectsXmlDTO {


    @XmlElement(name = "project")
    private List<ProjectXmlDTO> projects;


    public ProjectsXmlDTO() {
    }

    public List<ProjectXmlDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectXmlDTO> projects) {
        this.projects = projects;
    }
}
