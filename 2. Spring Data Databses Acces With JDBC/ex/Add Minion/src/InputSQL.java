public class InputSQL {

    public static final String checkVillain = "SELECT * FROM `villains` WHERE `name` = ?;";
    public static final String checkTown = "SELECT * FROM `towns` WHERE `name` = ?;";
    public static final String addTown = "INSERT INTO `towns` (`name`,`country`) VALUE(?, ?);";
    public static final String addVillain = "INSERT INTO `villains` (`name`,`evilness_factor`) VALUE (?,'evil');";
    public static final String addMinion = "INSERT INTO `minions` (`name`,`age`,`town_id`) VALUE(?, ?, ?);";


 }
