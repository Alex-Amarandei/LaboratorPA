/**
 * @author Alex Amarandei
 */

package compulsory;

import compulsory.dao.GenreDAO;
import compulsory.dao.MovieDAO;
import compulsory.dao.MovieGenreDAO;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();
        GenreDAO genreDAO = new GenreDAO();
        MovieGenreDAO movieGenreDAO = new MovieGenreDAO();

        genreTest(genreDAO);
        movieTest(movieDAO);
        movieGenreTest(movieGenreDAO, movieDAO, genreDAO);
    }

    static void genreTest(GenreDAO genreDAO) {
        genreDAO.createGenre("Action");
        genreDAO.createGenre("Drama");
        genreDAO.createGenre("SF");

        System.out.println("Action Genre's id is: " + genreDAO.getID("Action"));
        System.out.println(genreDAO.getGenre(3));
        System.out.println();
    }

    static void movieTest(MovieDAO movieDAO) {
        movieDAO.createMovie("John Wick", Date.valueOf(LocalDate.of(2014, 9, 19)), 101, 4);
        movieDAO.createMovie("Anne Frank: The Whole Story", Date.valueOf(LocalDate.of(2001, 5, 21)), 190, 5);
        movieDAO.createMovie("Interstellar", Date.valueOf(LocalDate.of(2014, 11, 6)), 169, 5);

        System.out.println("Interstellar's id is: " + movieDAO.getID("Interstellar"));
        System.out.println(movieDAO.getMovie(1));
        System.out.println();
    }

    static void movieGenreTest(MovieGenreDAO movieGenreDAO, MovieDAO movieDAO, GenreDAO genreDAO) {
        movieGenreDAO.assignGenreToMovie(genreDAO.getGenre(1), movieDAO.getMovie(1));
        // will continue to add tests and utility in the optional part
    }
}
