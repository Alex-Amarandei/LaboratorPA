package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.Genre;
import optional.models.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class MovieGenreDAO {
    public void assignMovieToGenre(Movie movie, Genre genre) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movie_genres VALUES(?, ?);")) {
            preparedStatement.setString(1, movie.getId());
            preparedStatement.setInt(2, genre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignMovieToGenres(Movie movie, List<Genre> genres) {
        for (Genre genre : genres) {
            try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movie_genres VALUES(?, ?);")) {
                preparedStatement.setString(1, movie.getId());
                preparedStatement.setInt(2, genre.getId());
                preparedStatement.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Duplicate entry. Insert operation cancelled.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Movie> getMoviesOfGenre(Genre genre) {
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT id, title, original_title, year, release_date, duration, country, language, description, score FROM movie_genres JOIN movies ON movie_id = id WHERE genre_id = ?;")) {
            preparedStatement.setInt(1, genre.getId());
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
            System.out.println(e.getMessage());
        }

        return movies;
    }

    public List<Genre> getGenresOfMovie(Movie movie) {
        List<Genre> genres = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT id, name FROM movie_genres JOIN genres ON genre_id = id WHERE movie_id = ?;")) {
            preparedStatement.setString(1, movie.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
                genres.add(genre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return genres;
    }
}
