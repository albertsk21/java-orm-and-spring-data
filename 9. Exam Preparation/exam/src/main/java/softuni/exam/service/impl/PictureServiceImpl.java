package softuni.exam.service.impl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.contraints.Output;
import softuni.exam.dto.xml.importxml.PictureDtoXmlInput;
import softuni.exam.dto.xml.importxml.PicturesRootXmlInput;
import softuni.exam.modelDB.Picture;
import softuni.exam.paths.Path;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class PictureServiceImpl implements PictureService {

    private XmlParser xmlParser;
    private PictureRepository pictureRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private boolean areImported = false;

    public PictureServiceImpl(XmlParser xmlParser, PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPictures() throws IOException, JAXBException {

        StringBuilder output = new StringBuilder();
        PicturesRootXmlInput picturesXml = this.xmlParser.parseXml(PicturesRootXmlInput.class,Path.PICTURES_PATH_XML);

        for (PictureDtoXmlInput pictureXml: picturesXml.getPictures()) {

            if(pictureXml.getUrl() == null){
                output.append(Output.INVALID_PICTURE);
            }else{
                Picture picture = this.modelMapper.map(pictureXml,Picture.class);
                this.pictureRepository.saveAndFlush(picture);
                output.append(String.format(Output.ADD_SUCCESSFULLY_PICTURE, pictureXml.getUrl()));
            }
        }



       this.areImported = true;
       return output.toString();
    }

    @Override
    public boolean areImported() {
        return this.areImported;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
      return Files.readString(java.nio.file.Path.of(Path.PICTURES_PATH_XML));
    }
}
