package compulsory.app;
import compulsory.entity.Movie;
import compulsory.repo.MovieRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Date;

public class MovieManager {

    public static void main(String[] args) {
        EntityManagerFactory factory = compulsory.util.PersistenceUtil.getFactory();
        EntityManager em = factory.createEntityManager();
        MovieRepository movieRepository = new MovieRepository(em);
        em.getTransaction().begin();

        Movie movie1 = new Movie("movie1", "title1", "original_title1", 2000, Date.valueOf("2000-04-11"), 100, "Romania", "Romanian", "Groundbreaking", 9.5);
        movieRepository.create(movie1);
        Movie movie2 = new Movie("movie2", "title2", "original_title2", 2000, Date.valueOf("2000-04-21"), 120, "USA", "English", "Outstanding", 9.0);
        movieRepository.create(movie2);
        Movie movie3 = new Movie("movie3", "title3", "original_title3", 2000, Date.valueOf("2000-08-21"), 140, "UK", "English", "Innovative", 9.8);
        movieRepository.create(movie3);

        Movie movie4 = movieRepository.findByName("title3").get(0);
        Movie movie5 = movieRepository.findByID("movie2");
        System.out.println(movie4);
        System.out.println(movie5);
        em.getTransaction().commit();
        em.close();
        factory.close();

    }
}
