package optional.dao;

import optional.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class LoginDAO {
    public void log(String username) {
        UserDAO userDAO = new UserDAO();
        if (!userDAO.exists(username)) {
            System.err.println(username + " does not exist. Operation cancelled.");
            return;
        }

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO logs VALUES(?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("User is already logged in.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User logged in successfully.");
    }

    public Boolean isLogged(String username) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM logs WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
