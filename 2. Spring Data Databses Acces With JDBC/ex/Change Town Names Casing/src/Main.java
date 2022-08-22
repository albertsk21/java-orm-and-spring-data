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

        String country = bfr.readLine();

        statement = connection.prepareStatement(InputSQL.SelectAllTowns);
        statement.setString(1,country);
        resultSet = statement.executeQuery();
        if(!check(resultSet)){
            System.out.println(Output.NOTOWNSAFECTED);
            return;
        }

        statement = connection.prepareStatement(InputSQL.SelectAllTowns);
        statement.setString(1,country);
        resultSet = statement.executeQuery();
        List<Town> townsList = new ArrayList<>();
        while(resultSet.next()){
            Town town =  new Town(resultSet.getString("name"),resultSet.getInt("id"));
            townsList.add(town);
        }



        for (Town town : townsList ) {

            statement = connection.prepareStatement(InputSQL.UpdateTown);
            statement.setString(1,town.getName().toUpperCase());
            statement.setInt(2,town.getId());
            statement.executeUpdate();
        }

//        ----------------------------OUTPUT---------------------------------------


            System.out.printf(Output.TOWNSAFECTED,townsList.size());
            StringBuilder output = new StringBuilder("[");

            int counter = 0;

            statement = connection.prepareStatement(InputSQL.SelectAllTowns);
            statement.setString(1,country);
            resultSet = statement.executeQuery();
            while(resultSet.next()){

                if(counter == townsList.size() - 1){
                    output.append(String.format("%s",resultSet.getString("name")));
                }else{
                    output.append(String.format("%s, ",resultSet.getString("name")));
                }
                counter++;
             }


            output.append("]");
        System.out.println(output);
    }


    public static boolean check( ResultSet resultSet) throws SQLException {
        int index = -1;
        while (resultSet.next()){
            index++;
        }
        return index > -1;
    }

}
