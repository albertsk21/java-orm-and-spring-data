package car.util.impl;




import car.util.interfaces.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {
    @Override
    public <T> T parseXml(Class<T> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File file = new File(filePath);
        return (T) unmarshaller.unmarshal(file);

    }


    @Override
    public void parseOutput(Object object, String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        OutputStream outputStream = new FileOutputStream(filePath);       // Setting the output file
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(outputStream));
        jaxbMarshaller.marshal(object,bufferedWriter);
    }


}
