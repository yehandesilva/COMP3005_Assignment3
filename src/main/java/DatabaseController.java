import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    private Connection connection;
    private Statement statement;

    public static final String CREATION_FILEPATH = "src/main/resources/creationSQL.txt";
    public static final String INSERTION_FILEPATH = "src/main/resources/insertionSQL.txt";

    public DatabaseController(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Connection to database failed");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setupTable() {
        try {
            statement.executeUpdate(this.parseSQLFile(CREATION_FILEPATH));
            statement.executeUpdate(this.parseSQLFile(INSERTION_FILEPATH));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void closeController() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void getAllStudents() {

    }

    public void addStudent(String first_name, String last_name, String email, String enrollment_date) {

    }

    public void updateStudentEmail(int student_id, String new_email) {

    }

    public void deleteStudent(int student_id) {

    }

    public String parseSQLFile(String filename) {
        String line;
        StringBuilder sqlQuery = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                sqlQuery.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sqlQuery.toString();
    }

}
