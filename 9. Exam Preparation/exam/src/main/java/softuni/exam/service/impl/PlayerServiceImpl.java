package softuni.exam.service.impl;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.contraints.Output;
import softuni.exam.dto.json.importjson.PlayerDtoJsonInput;
import softuni.exam.entity.ValidatePlayer;
import softuni.exam.enums.Position;
import softuni.exam.modelDB.Picture;
import softuni.exam.modelDB.Player;
import softuni.exam.modelDB.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PlayerService;
import softuni.exam.util.interfaces.XmlParser;


import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {


    private XmlParser xmlParser;
    private TeamRepository teamRepository;
    private PictureRepository pictureRepository;
    private PlayerRepository playerRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private boolean areImported = false;

    public PlayerServiceImpl(XmlParser xmlParser, TeamRepository teamRepository, PictureRepository pictureRepository, PlayerRepository playerRepository, Gson gson, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.playerRepository = playerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }



    @Override
    public String importPlayers() throws IOException {

        PlayerDtoJsonInput[] players =this.gson.fromJson(this.readPlayersJsonFile(),PlayerDtoJsonInput[].class);


        StringBuilder output = new StringBuilder();
        for (PlayerDtoJsonInput playerJson : players) {

            if(!this.isPlayerValid(playerJson)){
                output.append(Output.INVALID_PLAYER);
            }else{
                Player player = this.convertPlayerJsonInPlayerDB(playerJson);
                this.playerRepository.saveAndFlush(player);
                output.append(String.format(Output.ADD_SUCCESSFULLY_PLAYER,player.getFirstName(),player.getLastName()));
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
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(softuni.exam.paths.Path.PLAYERS_PATH_JSON));
    }

    @Override
    public String exportPlayersWithALargerSalaryThanTheGiven() {
        StringBuilder output = new StringBuilder();
        for (Player player : this.playerRepository.findAllPlayers()) {


            if(player.getSalary().doubleValue() >  100000){
                output.append(String.format(Output.EXPORT_PLAYER_BY_SALARY,player.getFirstName(),player.getLastName(),player.getNumber()));
            }

        }

       return output.toString();
    }

    @Override
    public String exportPlayersInATeam() {


        String teamName = null;


        StringBuilder output = new StringBuilder();
        Team team = this.teamRepository.findByName("North Hub");
        for (Player player : team.getPlayers()) {


            if(teamName == null){
                 teamName = player.getTeam().getName();
                 output.append(String.format("%s\n",teamName));
            }


            output.append(String.format(Output.EXPORT_PLAYER_BY_TEAM,player.getFirstName(),player.getLastName(),player.getPosition(),player.getNumber()));




        }


        return output.toString();
    }



    private boolean isPlayerValid(PlayerDtoJsonInput playerJson){

        try {
            ValidatePlayer validatePlayer = new ValidatePlayer(
                    playerJson.getFirstName(),
                    playerJson.getLastName(),
                    playerJson.getNumber(),
                    playerJson.getPosition(),
                    playerJson.getSalary());

            Picture picture = this.pictureRepository.findByUrl(playerJson.getPicture().getUrl());
            Team team = this.teamRepository.findByName(playerJson.getTeam().getName());

            if(picture == null || team == null ){
                return false;
            }

        }catch (IllegalArgumentException argumentException){
            return false;
        }



        return true;
    }
    private Player convertPlayerJsonInPlayerDB(PlayerDtoJsonInput playerJson){
        Player player = this.modelMapper.map(playerJson,Player.class);
        player.setTeam(this.teamRepository.findByName(playerJson.getTeam().getName()));
        player.setPicture(this.pictureRepository.findByUrl(playerJson.getPicture().getUrl()));

        Position position = this.findPosition(playerJson.getPosition());
        player.setPosition(position);

        return player;


    }
    private Position findPosition(String positionName){


        switch (positionName){
            case "GK":
                return Position.GK;
            case "CD":
                return Position.CD;
            case "RB":
                return Position.RB;
            case "LB":
                return Position.LB;
            case "CM":
                return Position.CM;
            case "DM":
                return Position.DM;
            case "CDM":
                return Position.CDM;
            case "LM":
                return Position.LM;
            case "RM":
                return Position.RM;
            case "ST":
                return Position.ST;
            case "CF":
                return Position.CF;
            case "RW":
                return Position.RW;
            case "LW":
                return Position.LW;
            default:
                return null;
        }

    }

}
