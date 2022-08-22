package softuni.exam.service.impl;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.contraints.Output;
import softuni.exam.dto.xml.importxml.TeamDtoXmlInput;
import softuni.exam.dto.xml.importxml.TeamsRootXmlInput;
import softuni.exam.modelDB.Picture;
import softuni.exam.modelDB.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.TeamService;
import softuni.exam.util.interfaces.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {



    private XmlParser xmlParser;
    private TeamRepository teamRepository;
    private PictureRepository pictureRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private boolean areImported = false;


    public TeamServiceImpl(XmlParser xmlParser, TeamRepository teamRepository, PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importTeams() throws JAXBException {

        TeamsRootXmlInput teamsXml = this.xmlParser.parseXml(TeamsRootXmlInput.class, softuni.exam.paths.Path.TEAMS_PATH_XML);
        StringBuilder output = new StringBuilder();

        for (TeamDtoXmlInput teamXml : teamsXml.getTeamsXml()) {


            Picture picture = this.pictureRepository.findByUrl(teamXml.getPicture().getUrl());

            if(picture == null){
                output.append(Output.INVALID_TEAM);
            }else{
                Team team = this.modelMapper.map(teamXml,Team.class);
                team.setPicture(picture);
                this.teamRepository.saveAndFlush(team);
                output.append(String.format(Output.ADD_SUCCESSFULLY_TEAM,team.getName()));
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
    public String readTeamsXmlFile() throws IOException {
        return Files.readString(Path.of(softuni.exam.paths.Path.TEAMS_PATH_XML));
    }

}
