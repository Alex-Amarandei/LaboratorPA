/**
 * @author Alex Amarandei
 */

package compulsory.dao;

import compulsory.database.DatabaseConnection;
import compulsory.models.Movie;

import java.sql.*;

public class MovieDAO {
    public void createMovie(String title, Date releaseDate, Integer duration, Integer score) {
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movies(title, release_date, duration, score) VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, title);
            preparedStatement.setDate(2, releaseDate);
            preparedStatement.setInt(3, duration);
            preparedStatement.setInt(4, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getID(String title) {
        Integer id = -1;

        try(Statement statement = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id FROM movies WHERE title = '" + title + "'");
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


    public Movie getMovie(Integer id) {
        Movie movie = new Movie();

        try(Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM movies WHERE id = " + id);
            rs.next();
            movie = new Movie(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }
}