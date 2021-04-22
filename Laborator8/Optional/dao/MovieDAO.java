package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    public void create(String id, String title, String originalTitle, Integer year, Date releaseDate, Integer duration, String country, String language, String description, Double score) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movies VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, originalTitle);
            preparedStatement.setInt(4, year);
            preparedStatement.setDate(5, releaseDate);
            preparedStatement.setInt(6, duration);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, language);
            preparedStatement.setString(9, description);
            preparedStatement.setDouble(10, score);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Movie movie) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movies VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setString(1, movie.getId());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setString(3, movie.getOriginalTitle());
            preparedStatement.setInt(4, movie.getYear());
            preparedStatement.setDate(5, movie.getReleaseDate());
            preparedStatement.setInt(6, movie.getDuration());
            preparedStatement.setString(7, movie.getCountry());
            preparedStatement.setString(8, movie.getLanguage());
            preparedStatement.setString(9, movie.getDescription());
            preparedStatement.setDouble(10, movie.getScore());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getByName(String title) {
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM movies WHERE title = ?")) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getDouble(10)
                );
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }


    public Movie getById(String id) {
        Movie movie = new Movie();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM movies WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            movie = new Movie(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4), resultSet.getDate(5),
                    resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9), resultSet.getDouble(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }
}