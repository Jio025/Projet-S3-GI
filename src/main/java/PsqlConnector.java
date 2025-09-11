import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class PsqlConnector {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");
            // Perform database operations here
            Statement statement = connection.createStatement();
            Statement statement_create = connection.createStatement();
            ResultSet resultSet_create = statement_create.executeQuery("INSERT INTO DockerConfigs (base_image)\n" +
                    "            VALUES ('ubuntu:20.04')\n" +
                    "            RETURNING config_id;");
            System.out.println("Querry created");
            ResultSet resultSet = statement.executeQuery("SELECT base_image FROM DockerConfigs");
            while (resultSet.next()) {
                System.out.println("Column1: " + resultSet.getString("base_image")); }

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}