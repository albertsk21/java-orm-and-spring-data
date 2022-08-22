import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String... args) throws SQLException, IOException {


        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

//        ------------------------------VARIABLES---------------------------------
        String user = "root";
        String password = "Albert.nustiu.2002";
        String url = "jdbc:mysql://localhost:3306/minions_db_access_to_jdbc";
        String sql = "SELECT * , m.`name` AS `minion_name` FROM  `villains` AS v\n" +
                "JOIN `minions_villains` AS mv ON v.`id` = mv.`villain_id`\n" +
                "JOIN `minions` AS m ON mv.`minion_id` = m.`id` \n" +
                "WHERE v.`id` = ?;";
//        ------------------------CONNECTION-TO-DATABASE--------------------------

        Properties properties= new Properties();
        properties.setProperty("user",user);
        properties.setProperty("password",password);
        Connection connection = DriverManager.getConnection(url,properties);
        PreparedStatement statement = connection.prepareStatement(sql);

//        -----------------------------LOGIC---------------------------------------


        String id = bfr.readLine();
        statement.setInt(1,Integer.parseInt(id));

        Map<String, List<Minion>> villainMap =  new LinkedHashMap<>();
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){

            List<Minion> minionsList = new ArrayList<>();
            String villain = resultSet.getString("name");
            String minionName = resultSet.getString("minion_name");
            int minionAge = resultSet.getInt("age");
            Minion minion =  new Minion(minionName,minionAge);


            if(villainMap.containsKey(villain)){
                minionsList = villainMap.get(villain);

            }
            minionsList.add(minion);
            villainMap.put(villain,minionsList);

        }

//        ----------------------------OUTPUT---------------------------------------


        for (String villain: villainMap.keySet()) {
            System.out.printf("Villain: %s%n",villain);
            List<Minion> minions = villainMap.get(villain);

            int counter = 1;
            for (Minion minion : minions) {
                System.out.printf("%d. %s %d%n",counter,minion.getName(),minion.getAge());
                counter++;
            }


        }


    }
}
