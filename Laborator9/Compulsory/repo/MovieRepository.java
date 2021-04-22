package compulsory.repo;

import compulsory.entity.Movie;

import javax.persistence.EntityManager;
import java.util.List;

public class MovieRepository {

    private EntityManager em;

    public MovieRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Movie movie) {
        em.persist(movie);
    }

    public Movie findByID(String id) {
        return em.find(Movie.class, id);
    }

    public List<Movie> findByName(String name) {
        return em.createNamedQuery("Movie.findByName").setParameter("name", name).getResultList();
    }

}