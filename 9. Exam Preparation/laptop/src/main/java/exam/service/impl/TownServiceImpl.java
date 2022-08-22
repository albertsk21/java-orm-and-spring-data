package exam.service.impl;

import com.google.gson.Gson;
import exam.config.interfaces.XmlParser;
import exam.data.entity.xml.TownDtoInputXml;
import exam.data.entity.xml.TownRootInputXml;
import exam.data.model.Town;
import exam.path.PathsProject;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private TownRepository townRepository;
    private Gson gson;

    public TownServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, TownRepository townRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.townRepository = townRepository;
        this.gson = gson;
    }





    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(PathsProject.TOWNS_XML));
    }

    @Override
    public String importTowns() throws JAXBException{

        TownRootInputXml townsXml = this.xmlParser.parseXml(TownRootInputXml.class,PathsProject.TOWNS_XML);
        StringBuilder output = new StringBuilder();

        for (TownDtoInputXml townXml : townsXml.getTowns() ) {

            try{

                Town town = this.convertTownXml(townXml);
                this.townRepository.saveAndFlush(town);

                output.append(String.format("Successfully imported Town %s\n",town.getName()));
            }catch (IllegalArgumentException exception){
                output.append("Invalid town\n");
            }

        }

        return output.toString();
    }

    private Town convertTownXml(TownDtoInputXml townXml){

        Town town = new Town();
        town.setName(townXml.getName());
        town.setPopulation(townXml.getPopulation());
        town.setTravelGuide(townXml.getTravelGuider());
        this.throwExceptionIfExistTown(townXml.getName());
        return town;
    }


    private void throwExceptionIfExistTown(String nameTown){
        Town town = this.townRepository.findTownByName(nameTown);
        if(town != null){
            throw new IllegalArgumentException();
        }

    }
}
