import java.util.ArrayList;

/**
 * Main class to demonstrate DatabaseController. Showcase its functions and displays results.
 */
public class Main {

    /**
     * Print contents of an array list.
     * @param results Array to print contents of.
     */
    private void displayResults(ArrayList<String> results) {
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
        controller.setupTable();
        controller.closeController();

    }
}
