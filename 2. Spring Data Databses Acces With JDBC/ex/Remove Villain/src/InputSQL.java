public class InputSQL {
    public static final String DeleteIdFromMinionsVillain = "DELETE FROM `minions_villains` WHERE `villain_id` = ?;";
    public static final String DeleteVillain = "DELETE FROM `villains` WHERE `id` = ?;";
    public static final String checkId = "SELECT * FROM `villains` WHERE id = ?;";
    public static final String getVillain ="SELECT v.`id`,v.`name`AS `villain_name`, COUNT(m.`id`) AS `minions` FROM `villains` AS v\n" +
                                           "LEFT JOIN `minions_villains` AS mv ON v.`id` = mv.`villain_id`\n" +
                                           "LEFT JOIN `minions` AS m ON mv.`minion_id` = m.`id`\n" +
                                           "GROUP BY v.`id`\n" +
                                           "HAVING v.`id` = ?;";




 }
