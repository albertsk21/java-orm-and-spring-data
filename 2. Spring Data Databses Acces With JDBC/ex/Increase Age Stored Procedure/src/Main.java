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

        statement = connection.prepareStatement(InputSQL.callProcedureIncreaseAgeWithOneById);
        statement.setInt(1,id);
        statement.executeQuery();





//       ---------------------------OPTIONAL-OUTPUT--------------------------------


        statement = connection.prepareStatement(InputSQL.SelectAllMinions);
        resultSet = statement.executeQuery();

        while(resultSet.next()){
            System.out.printf("%s %d%n",resultSet.getString("name"),resultSet.getInt("age"));
        }


    }
    public static boolean check( ResultSet resultSet) throws SQLException {
        int index = -1;
        while (resultSet.next()){
            index++;
        }
        return index > -1;
    }

}
