package softuni.exam.instagraphlite.config.impl;

import softuni.exam.instagraphlite.config.interfaces.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParserImpl implements XmlParser {

    @Override
    public <T> T export(Class<T> object, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(path);
        return (T) unmarshaller.unmarshal(file);
    }
}
