package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.Movie;
import optional.models.Person;
import optional.models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class RoleCastDAO {
    public void assignMovieRoleToPerson(Movie movie, Role role, Person person) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO roles VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, movie.getId());
            preparedStatement.setString(2, String.valueOf(role));
            preparedStatement.setString(3, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> getMoviesOf(Person person, Role role) {
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT id, title, original_title, year, release_date, duration, country, language, description, score FROM roles JOIN movies ON id = movie_id WHERE person_id = ? AND role = ?")) {
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, String.valueOf(role));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                movies.add(
                        new Movie(
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
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return movies;
    }

    public List<Person> getCastOf(Movie movie) {
        List<Person> cast = new ArrayList<>();

        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT id, actor_name, real_name, date_of_birth, date_of_death FROM roles JOIN persons ON id = person_id WHERE movie_id = ?")) {
            preparedStatement.setString(1, movie.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                cast.add(
                        new Person (
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4),
                                resultSet.getDate(5)
                        )
                );
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cast;
    }
}
