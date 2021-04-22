package optional.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Lab8", "root", "root");

        return connection;
    }
}