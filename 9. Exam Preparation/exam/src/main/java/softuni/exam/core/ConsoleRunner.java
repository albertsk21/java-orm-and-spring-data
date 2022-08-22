package softuni.exam.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.exam.service.PictureService;
import softuni.exam.service.PlayerService;
import softuni.exam.service.TeamService;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private PictureService pictureService;
    private TeamService teamService;
    private PlayerService playerService;


    public ConsoleRunner(PictureService pictureService, TeamService teamService, PlayerService playerService) {
        this.pictureService = pictureService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
