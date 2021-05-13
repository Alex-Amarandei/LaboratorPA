package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void create(String username, String firstName, String lastName) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO users VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("User registered successfully.");
    }

    public void create(User user) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO users VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("User registered successfully.");
    }

    public User getByUsername(String username) {
        User user = new User();
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getByName(String firstName, String lastName) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM users WHERE first_name = ? AND last_name = ?")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(
                        new User(resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Boolean exists(String username) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void read(String username) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM messages WHERE receiver = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("FROM: " + resultSet.getString(2));
                System.out.println("TEXT: " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Messages read successfully.");
    }
}