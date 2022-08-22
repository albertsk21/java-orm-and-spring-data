public class InputSql {


    public static String checkTown = "SELECT * FROM `towns` WHERE `name` = ?;";
    public static String checkVillain = "SELECT * FROM `villains` WHERE `name` = ?; ";
    public static final String addTown = "INSERT INTO `towns` (`name`,`country`) VALUE(?, ?);";
    public static final String addVillain = "INSERT INTO `villains` (`name`,`evilness_factor`) VALUE (?,'evil');";
    public static final String addMinion = "INSERT INTO `minions` (`name`,`age`,`town_id`) VALUE(?, ?, ?);";
    public static final String getNumberOfMinionsByVillainName = "SELECT COUNT(m.`id`) AS `numbers_of_minions` FROM `minions` AS m \n" +
            "JOIN `minions_villains` AS mv  ON mv.`minion_id` = m.`id`\n" +
            "JOIN `villains` AS v ON mv.`villain_id` = v.`id`\n" +
            "WHERE v.`name` = ?;";
    public static final String getVillainNameById = "SELECT `name` FROM `villains` WHERE `id` = ?;";
    public static final String deleteVillainById = "DELETE FROM `villains` WHERE `id` = ?;";
    public static final String deleteIdFromMinionsVillain = "DELETE FROM `minions_villains` WHERE `villain_id` = ?;";
    public static final String getNameMinions = "SELECT `name` FROM `minions`;";
    public static final String increaseAgeByOneById = "UPDATE  `minions` SET `age` = `age` + 1\n" +
            "WHERE `id` = ?;";
    public static final String getInfoMinions = "SELECT `id`, `name`, `age` FROM `minions`;";
    public static final String callProcedureIncreaseAge = "CALL `increase_age`(?);";
}
