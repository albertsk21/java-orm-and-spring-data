package softuni.exam.instagraphlite.config.interfaces;

import javax.xml.bind.JAXBException;

public interface XmlParser {

   <T> T export(Class<T> object, String path) throws JAXBException;
}
