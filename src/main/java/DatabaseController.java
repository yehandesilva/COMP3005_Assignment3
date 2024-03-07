import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<String> getAllStudents() {
        ArrayList<String> allStudents = new ArrayList<>();
        try {
            statement.executeQuery("SELECT * FROM students");
            ResultSet returnSet = statement.getResultSet();
            while (returnSet.next()) {
                allStudents.add(String.format("student_id: %d, first_name: %s, last_name: %s, email: %s, enrollment_date: %s", returnSet.getInt("student_id"), returnSet.getString("first_name"), returnSet.getString("last_name"), returnSet.getString("email"), returnSet.getString("enrollment_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return allStudents;
    }

    public void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        try {
            statement.executeUpdate(String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('%s', '%s', '%s', '%s');", first_name, last_name, email, enrollment_date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void updateStudentEmail(int student_id, String new_email) {
        try {
            statement.executeUpdate(String.format("UPDATE students SET email='%s' WHERE student_id=%d;", new_email, student_id));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void deleteStudent(int student_id) {
        try {
            statement.executeUpdate(String.format("DELETE FROM students WHERE student_id=%d", student_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
