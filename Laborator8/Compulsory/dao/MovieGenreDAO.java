/**
 * @author Alex Amarandei
 */

package compulsory.dao;

import compulsory.database.DatabaseConnection;
import compulsory.models.Genre;
import compulsory.models.Movie;
import java.sql.*;

public class MovieGenreDAO {
    public void assignGenreToMovie(Genre genre, Movie movie) {
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO movie_genres VALUES(?, ?);")) {
            MovieDAO movieDAO = new MovieDAO();
            GenreDAO genreDAO = new GenreDAO();

            preparedStatement.setInt(1, genreDAO.getID(genre.getName()));
            preparedStatement.setInt(2, movieDAO.getID(movie.getTitle()));
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
