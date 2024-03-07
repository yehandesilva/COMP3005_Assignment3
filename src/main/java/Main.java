import java.util.ArrayList;

/**
 * Main class to demonstrate DatabaseController. Showcase its functions and displays results.
 *
 * @author YehanDeSilva 101185388
 * @version 1
 */
public class Main {

    /**
     * Print contents of an array list.
     * @param results Array to print contents of.
     */
    public static void displayResults(ArrayList<String> results) {
        for (String result: results) {
            System.out.println(result);
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        // Setup information
        String url = "jdbc:postgresql://localhost:5432/COMP3005-ASSIGNMENT3";
        String user = "postgres";
        String password = "admin";

        // Database controller commands
        DatabaseController controller = new DatabaseController(url, user, password);

        // 1) Setup table and fill with sample data
        controller.setupTable();

        // 2) Display sample data
        Main.displayResults(controller.getAllStudents());

        // 3) Add new student
        controller.addStudent("Yehan", "De Silva", "yehandesilva@cmail.carleton.ca", "2024-01-01");

        // 4) Update student email
        controller.updateStudentEmail(1, "johndoe2@example.com");

        // 5) Delete student
        controller.deleteStudent(2);

        // 6) Display new data
        System.out.println();
        Main.displayResults(controller.getAllStudents());

        // 7)  connection
        controller.closeController();

    }
}
