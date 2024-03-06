import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/COMP3005-ASSIGNMENT3";
        String user = "postgres";
        String password = "admin";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection == null) {
                System.out.println("Connection to database failed");
                throw new RuntimeException();
            } else {
                System.out.println("Connected to database");
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
