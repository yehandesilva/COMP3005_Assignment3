public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/COMP3005-ASSIGNMENT3";
        String user = "postgres";
        String password = "admin";

        DatabaseController controller = new DatabaseController(url, user, password);
        controller.setupTable();
        controller.closeController();

    }
}
