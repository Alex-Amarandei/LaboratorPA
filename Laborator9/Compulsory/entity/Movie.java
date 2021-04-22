package compulsory.entity;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movies")
@NamedQueries({
        @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.title LIKE :name")
})
public class Movie {
    private String id;
    private String title;
    private String originalTitle;
    private Integer year;
    private Date releaseDate;
    private Integer duration;
    private String country;
    private String language;
    private String description;
    private Double score;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Movie() {
        this.id = "id";
        this.title = "title";
        this.originalTitle = "originalTitle";
        this.year = 0;
        this.releaseDate = Date.valueOf(LocalDate.now());
        this.duration = 0;
        this.country = "country";
        this.language = "language";
        this.description = "description";
        this.score = 0.0;
    }

    public Movie(String id, String title, String originalTitle, Integer year, Date releaseDate, Integer duration, String country, String language, String description, Double score) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.year = year;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.description = description;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie that = (Movie) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(originalTitle, that.originalTitle) && Objects.equals(year, that.year) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(duration, that.duration) && Objects.equals(country, that.country) && Objects.equals(language, that.language) && Objects.equals(description, that.description) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, originalTitle, year, releaseDate, duration, country, language, description, score);
    }
}
