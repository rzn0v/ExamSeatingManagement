import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database
{

    static String username;
    
    public static void main(String[] args) 
    {
        System.out.println(username);
        String url = "jdbc:mysql://localhost:3306/examseating";
        String username = "username";
        String password = "pwd";
        System.out.println("Connecting to the database...");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected successfully!");
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database.");
            e.printStackTrace();
        }
    }
}