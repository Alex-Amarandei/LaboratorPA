/**
 * @author Alex Amarandei
 */

package compulsory.dao;

import compulsory.database.DatabaseConnection;
import compulsory.models.Genre;

import java.sql.*;

public class GenreDAO {
    public void createGenre(String name) {
        try(PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement("INSERT INTO genres(`name`) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getID(String name) {
        Integer id = -1;

        try(Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id FROM genres WHERE `name` = '" + name + "'");
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


    public Genre getGenre(Integer id) {
        Genre genre = new Genre();

        try(Statement stmt = DatabaseConnection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM genres WHERE id = " + id);
            rs.next();
            genre = new Genre(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genre;
    }
}
