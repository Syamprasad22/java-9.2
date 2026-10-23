import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(255) NOT NULL)";
        String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement createTableStatement = connection.createStatement()) {
            createTableStatement.executeUpdate(createTableSql);

            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, "John Doe");
                insertStatement.setString(2, "john.doe@example.com");
                insertStatement.executeUpdate();

                insertStatement.setString(1, "Jane Doe");
                insertStatement.setString(2, "jane.doe@example.com");
                insertStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
