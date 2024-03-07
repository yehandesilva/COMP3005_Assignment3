import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    private Connection connection;
    Statement statement;

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

}
