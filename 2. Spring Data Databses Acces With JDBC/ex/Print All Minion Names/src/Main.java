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



        Deque<String> minionsDeque = new ArrayDeque<>();
        statement = connection.prepareStatement(InputSQL.GetAllMinionName);
        resultSet = statement.executeQuery();

        while(resultSet.next()){
            minionsDeque.add(resultSet.getString("name"));
        }



        List<String> outputMinions = new ArrayList<>();

        while (!minionsDeque.isEmpty()){
            outputMinions.add(minionsDeque.pollFirst());
            outputMinions.add(minionsDeque.pollLast());
        }

//       ---------------------------OPTIONAL-OUTPUT--------------------------------


        for (String minionName : outputMinions) {
            System.out.println(minionName);
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
