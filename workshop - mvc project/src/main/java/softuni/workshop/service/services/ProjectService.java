package softuni.workshop.service.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ProjectService {

    void importProjects() throws JAXBException, IllegalAccessException, InstantiationException;

    boolean areImported();

    String readProjectsXmlFile() throws IOException;

    String exportFinishedProjects();
}
