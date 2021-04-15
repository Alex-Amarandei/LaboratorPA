/**
 * @author Alex Amarandei
 */

package compulsory.database;

import java.sql.*;

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