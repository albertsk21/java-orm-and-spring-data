package softuni.workshop.data.dtos.xml.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootXmlInput {


    @XmlElement(name = "project")
    private List<ProjectDtoXmlInput> projects;


    public ProjectRootXmlInput() {
    }

    public List<ProjectDtoXmlInput> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDtoXmlInput> projects) {
        this.projects = projects;
    }
}
