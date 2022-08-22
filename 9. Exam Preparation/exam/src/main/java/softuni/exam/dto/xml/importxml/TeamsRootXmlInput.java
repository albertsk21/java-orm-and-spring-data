package softuni.exam.dto.xml.importxml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamsRootXmlInput {


    @XmlElement(name = "team")
    private List<TeamDtoXmlInput> teamsXml = new ArrayList<>();


    public TeamsRootXmlInput() {
    }

    public List<TeamDtoXmlInput> getTeamsXml() {
        return teamsXml;
    }

    public void setTeamsXml(List<TeamDtoXmlInput> teamsXml) {
        this.teamsXml = teamsXml;
    }
}