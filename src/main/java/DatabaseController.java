import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Functions to query/update database.
 *
 * @author YehanDeSilva 101185388
 * @version 1
 */
public class DatabaseController {

    // Fields
    private Connection connection;
    private Statement statement;

    // Constants
    public static final String CREATION_FILEPATH = "src/main/resources/creationSQL.txt";
    public static final String INSERTION_FILEPATH = "src/main/resources/insertionSQL.txt";

    /**
     * Constructor for DatabaseController class.
     * @param url url of the database.
     * @param user username for authorization.
     * @param password password for authorization.
     */
    public DatabaseController(String url, String user, String password) {
        try {
            // Create connection and statement
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Connection to database failed");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Sets up table with the table schema and sample data.
     */
    public void setupTable() {
        try {
            // Parse files containing SQL queries before running them on database.
            statement.executeUpdate(this.parseSQLFile(CREATION_FILEPATH));
            statement.executeUpdate(this.parseSQLFile(INSERTION_FILEPATH));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Closes the controller.
     */
    public void closeController() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets all students in the students table.
     * @return all student tuples.
     */
    public ArrayList<String> getAllStudents() {
        ArrayList<String> allStudents = new ArrayList<>();
        try {
            statement.executeQuery("SELECT * FROM students");
            ResultSet returnSet = statement.getResultSet();
            // Convert return set to an array list of strings displaying each record.
            while (returnSet.next()) {
                allStudents.add(String.format("student_id: %d, first_name: %s, last_name: %s, email: %s, enrollment_date: %s",
                        returnSet.getInt("student_id"), returnSet.getString("first_name"),
                        returnSet.getString("last_name"), returnSet.getString("email"),
                        returnSet.getString("enrollment_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return allStudents;
    }

    /**
     * Adds specified student as new record.
     * @param first_name first name of student.
     * @param last_name last name of student.
     * @param email email of student.
     * @param enrollment_date enrollment date of student.
     */
    public void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        try {
            statement.executeUpdate(String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) " +
                    "VALUES ('%s', '%s', '%s', '%s');", first_name, last_name, email, enrollment_date));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Updates email attribute of specified student.
     * @param student_id student id of student to be updated.
     * @param new_email new email of student.
     */
    public void updateStudentEmail(int student_id, String new_email) {
        try {
            statement.executeUpdate(String.format("UPDATE students SET email='%s' WHERE student_id=%d;", new_email, student_id));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Deletes specified student.
     * @param student_id student id of student to be deleted.
     */
    public void deleteStudent(int student_id) {
        try {
            statement.executeUpdate(String.format("DELETE FROM students WHERE student_id=%d", student_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parses a txt file to return SQL queries.
     * @param filename filename to be parsed.
     * @return String containing all SQL queries.
     */
    public String parseSQLFile(String filename) {
        String line;
        StringBuilder sqlQuery = new StringBuilder();
        try {
            // Read each line and append to the returned string
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
