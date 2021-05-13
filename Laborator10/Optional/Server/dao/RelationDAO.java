package optional.dao;

import optional.database.DatabaseConnection;
import optional.exceptions.AlreadyFriendsException;
import optional.exceptions.NoUserLoggedInException;
import optional.exceptions.NotFriendsException;
import optional.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationDAO {
    public void friend(String username1, String username2) {
        UserDAO userDAO = new UserDAO();
        LoginDAO loginDAO = new LoginDAO();

        if (!userDAO.exists(username2)) {
            System.err.println(username2 + " does not exist. Operation cancelled.");
            return;
        }

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO relations VALUES(?, ?)")) {
            if (!loginDAO.isLogged(username1))
                throw new NoUserLoggedInException("No user is currently logged in.");

            if (!areFriends(username1, username2)) {
                preparedStatement.setString(1, username1);
                preparedStatement.setString(2, username2);
                preparedStatement.executeUpdate();
            } else throw new AlreadyFriendsException("The users are already friends.");
        } catch (NoUserLoggedInException | AlreadyFriendsException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Users are now friends.");
    }

    public Boolean areFriends(String username1, String username2) {
        List<User> user1Friends = getFriendList(username1);
        if (user1Friends.contains(new User(username2, "", ""))) return true;

        List<User> user2Friends = getFriendList(username2);
        if (user2Friends.contains(new User(username1, "", ""))) return true;

        return false;
    }

    public List<User> getFriendList(String username) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT username, first_name, last_name FROM relations JOIN users ON friend2 = username WHERE friend1 = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void sendMessage(String from, String to, String message) {
        UserDAO userDAO = new UserDAO();
        LoginDAO loginDAO = new LoginDAO();

        if (!userDAO.exists(to)) {
            System.err.println(to + " does not exist. Operation cancelled.");
            return;
        }

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO messages VALUES(?, ?, ?)")) {
            if (!loginDAO.isLogged(from))
                throw new NoUserLoggedInException("No user is currently logged in.");

            if (areFriends(from, to)) {
                preparedStatement.setString(1, to);
                preparedStatement.setString(2, from);
                preparedStatement.setString(3, message);
                preparedStatement.executeUpdate();
            } else throw new NotFriendsException("The users are not friends.");
        } catch (NoUserLoggedInException | NotFriendsException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User sent a message successfully.");
    }

    public void sendAll(String from, String message) {
        LoginDAO loginDAO = new LoginDAO();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO messages VALUES(?, ?, ?)")) {
            if (!loginDAO.isLogged(from))
                throw new NoUserLoggedInException("No user is currently logged in.");

            List<User> friends = getFriendList(from);
            friends.stream().filter(friend -> areFriends(from, friend.getUsername())).forEach(friend -> sendMessage(from, friend.getUsername(), message));
        } catch (NoUserLoggedInException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("User sent every friend a message successfully.");
    }
}
