package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class GenreDAO {
    public void create(String name) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO genres(name) VALUES (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Genre getByName(String name) {
        Genre genre = new Genre();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM genres WHERE name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            System.out.println("There is no such genre.");
        }

        return genre;
    }


    public Genre getById(Integer id) {
        Genre genre = new Genre();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM genres WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genre;
    }
}
