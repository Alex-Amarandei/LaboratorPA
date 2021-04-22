package optional.models;

import com.opencsv.bean.CsvBindByPosition;

import java.sql.Date;
import java.time.LocalDate;

public class Movie {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String originalTitle;

    @CsvBindByPosition(position = 3)
    private Integer year;

    @CsvBindByPosition(position = 4)
    private Date releaseDate;

    @CsvBindByPosition(position = 6)
    private Integer duration;

    @CsvBindByPosition(position = 7)
    private String country;

    @CsvBindByPosition(position = 8)
    private String language;

    @CsvBindByPosition(position = 13)
    private String description;

    @CsvBindByPosition(position = 14)
    private Double score;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return title + ", " +
                year + ", " +
                duration + ", " +
                description + ", " +
                score + "\n";
    }
}