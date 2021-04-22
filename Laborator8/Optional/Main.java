package optional;

import optional.IMDbImporters.IMDbMovieImporter;
import optional.IMDbImporters.IMDbPersonImporter;
import optional.dao.*;
import optional.models.Genre;
import optional.models.Movie;
import optional.models.Person;
import optional.models.Role;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IMDbImportMovies();
        IMDbImportPersons();

        testGenres();
        testMovieGenres();
        testRoleCast();
    }

    static void IMDbImportMovies() {
        IMDbMovieImporter imdbMovieImporter = new IMDbMovieImporter();
        MovieDAO movieDAO = new MovieDAO();

        List<Movie> IMDbMovies = imdbMovieImporter.getIMDbMovieList();
//        System.out.println(IMDbMovies);

        for(int i = 0; i < 5; ++i) movieDAO.create(IMDbMovies.get(i));
    }

    static void IMDbImportPersons() {
        IMDbPersonImporter imdbPersonImporter = new IMDbPersonImporter();
        PersonDAO personDAO = new PersonDAO();

        List<Person> IMDbPersons = imdbPersonImporter.getIMDbPersonList();
//        System.out.println(IMDbPersons);

        for(int i = 0; i < 5; ++i) personDAO.create(IMDbPersons.get(i));
    }

    static void testGenres() {
        GenreDAO genreDAO = new GenreDAO();
        genreDAO.create("Biography");
        genreDAO.create("Drama");
        genreDAO.create("History");

        System.out.println(genreDAO.getByName("Biography"));
        System.out.println(genreDAO.getById(2));
    }

    static void testMovieGenres() {
        MovieGenreDAO movieGenreDAO = new MovieGenreDAO();
        MovieDAO movieDAO = new MovieDAO();
        GenreDAO genreDAO = new GenreDAO();
        List<Genre> genres = new ArrayList<>();
        genres.add(genreDAO.getById(1));
        genres.add(genreDAO.getById(3));

        movieGenreDAO.assignMovieToGenre(movieDAO.getById("tt0000009"), genreDAO.getById(3));
        movieGenreDAO.assignMovieToGenres(
                movieDAO.getByName("The Story of the Kelly Gang").get(0), genres);
        movieGenreDAO.assignMovieToGenre(movieDAO.getById("tt0001892"), genreDAO.getById(2));

        System.out.println(movieGenreDAO.getMoviesOfGenre(genreDAO.getByName("Drama")));
        System.out.println();
        System.out.println(movieGenreDAO.getGenresOfMovie(movieDAO.getByName("The Story of the Kelly Gang").get(0)));
        System.out.println();
    }

    static void testRoleCast() {
        RoleCastDAO roleCastDAO = new RoleCastDAO();
        PersonDAO personDAO = new PersonDAO();
        MovieDAO movieDAO = new MovieDAO();

        roleCastDAO.assignMovieRoleToPerson(
                movieDAO.getByName("L'Inferno").get(0), Role.Actor, personDAO.getByName("Fred Astaire").get(0)
        );
        roleCastDAO.assignMovieRoleToPerson(
                movieDAO.getByName("Cleopatra").get(0), Role.Actor, personDAO.getByName("Fred Astaire").get(0)
        );
        roleCastDAO.assignMovieRoleToPerson(
                movieDAO.getByName("Cleopatra").get(0), Role.Director, personDAO.getByName("John Belushi").get(0)
        );
        roleCastDAO.assignMovieRoleToPerson(
                movieDAO.getByName("Cleopatra").get(0), Role.Writer, personDAO.getByName("Ingmar Bergman").get(0)
        );

        System.out.println(roleCastDAO.getMoviesOf(personDAO.getById("nm0000001"), Role.Actor));
        System.out.println(roleCastDAO.getCastOf(movieDAO.getByName("Cleopatra").get(0)));
    }
}
