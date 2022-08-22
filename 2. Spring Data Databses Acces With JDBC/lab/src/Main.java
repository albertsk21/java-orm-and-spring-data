import java.sql.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user",InfoDB.username);
        props.setProperty("password", InfoDB.password);
        Connection connection = DriverManager.getConnection(InfoDB.minionsDB, props);

        PreparedStatement statement;
        ResultSet resultSet;


//        ----- GET IDS
        String ids =  "2 1 4";
        for (String id : ids.split(" ")) {
            statement = connection.prepareStatement(InputSql.callProcedureIncreaseAge);
            statement.setInt(1,Integer.parseInt(id));
            statement.executeUpdate();
        }


        statement = connection.prepareStatement(InputSql.getInfoMinions);
        resultSet = statement.executeQuery();

        while(resultSet.next()){
            System.out.printf("%d %s %d%n",resultSet.getInt("id"),resultSet.getString("name").toLowerCase(),resultSet.getInt("age"));
        }
    }


    public static boolean check(ResultSet resultSet) throws SQLException {
        int counter = -1;
        while(resultSet.next()){
            counter++;
        }

        return !(counter == -1);
    }


}
