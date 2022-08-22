import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String... args) throws SQLException, IOException {

        Properties properties= new Properties();
        properties.setProperty("user",infoDatabases.user);
        properties.setProperty("password",infoDatabases.password);
        Connection connection = DriverManager.getConnection(infoDatabases.url,properties);
        PreparedStatement statement;
        ResultSet resultSet;

//        -----------------------------LOGIC---------------------------------------
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] minionInput = bfr.readLine().split(" ");
        String minionName = minionInput[1];
        int age = Integer.parseInt(minionInput[2]);
        String townName = minionInput[3];

        String[] villainInput = bfr.readLine().split(" ");
        String villainName = villainInput[1];


        statement = connection.prepareStatement(InputSQL.checkTown);
        statement.setString(1,townName);
        resultSet = statement.executeQuery();

        if(!check(resultSet)){
            statement = connection.prepareStatement(InputSQL.addTown);

            statement.setString(1,townName);
            statement.setString(2,villainName);
            statement.executeUpdate();
            System.out.printf("Town %s was added to the database.%n",townName);
        }

        statement = connection.prepareStatement(InputSQL.checkVillain);
        statement.setString(1,villainName);

        resultSet = statement.executeQuery();
        if(!check(resultSet)){

            statement = connection.prepareStatement(InputSQL.addVillain);
            statement.setString(1,villainName);
            statement.executeUpdate();

            System.out.printf("Villain %s was added to the database.%n",villainName);
        }

        statement = connection.prepareStatement(InputSQL.checkTown);
        statement.setString(1,townName);
        resultSet = statement.executeQuery();


        int townId = 0;
        while(resultSet.next()){
            townId = resultSet.getInt("id");
        }


        statement = connection.prepareStatement(InputSQL.addMinion);
        statement.setString(1,minionName);
        statement.setInt(2,age);
        statement.setInt(3,townId);
        statement.executeUpdate();


        System.out.printf("Successfully added %s to be minion of %s.%n",minionName,villainName);

//        ----------------------------OUTPUT---------------------------------------

    }


    public static boolean check( ResultSet resultSet) throws SQLException {
        int index = -1;
        while (resultSet.next()){
            index++;
        }
        return index > -1;
    }

}
