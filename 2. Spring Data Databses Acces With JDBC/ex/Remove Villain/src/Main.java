import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String... args) throws SQLException, IOException {

//        ----------------------CONNECTION-TO-DATABASE-----------------------------

        Properties properties= new Properties();
        properties.setProperty("user",infoDatabases.user);
        properties.setProperty("password",infoDatabases.password);
        Connection connection = DriverManager.getConnection(infoDatabases.url,properties);
        PreparedStatement statement;
        ResultSet resultSet;
//        -----------------------------LOGIC---------------------------------------
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(bfr.readLine());



        statement = connection.prepareStatement(InputSQL.checkId);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(!check(resultSet)){
            System.out.println(Output.NoVillainFound);
            return;
        }



        statement = connection.prepareStatement(InputSQL.getVillain);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();


        String name = "";
        int minions = 0;

        while(resultSet.next()){
            name = resultSet.getString("villain_name");
            minions = resultSet.getInt("minions");
        }

        statement = connection.prepareStatement(InputSQL.DeleteIdFromMinionsVillain);
        statement.setInt(1,id);
        statement.executeUpdate();


        statement = connection.prepareStatement(InputSQL.DeleteVillain);
        statement.setInt(1,id);
        statement.executeUpdate();

        System.out.printf(Output.VillainDeleted,name);
        System.out.printf(Output.MinionsReleased,minions);



//       ---------------------------OPTIONAL-OUTPUT--------------------------------

    }


    public static boolean check( ResultSet resultSet) throws SQLException {
        int index = -1;
        while (resultSet.next()){
            index++;
        }
        return index > -1;
    }

}
