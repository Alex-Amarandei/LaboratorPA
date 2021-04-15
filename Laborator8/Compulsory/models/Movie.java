/**
 * @author Alex Amarandei
 */

package compulsory.models;

import java.sql.Date;
import java.time.LocalDate;

public class Movie {
    private Integer id;
    private String title;
    private Date releaseDate;
    private Integer duration;
    private Integer score;

    public Movie() {
        this.id = 0;
        this.title = "";
        this.releaseDate = Date.valueOf(LocalDate.now());
        this.duration = 0;
        this.score = 0;
    }

    public Movie(Integer id, String title, Date releaseDate, Integer duration, Integer score) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Movie (" + "id: " + id + ", title: " + title
                + ", released: " + releaseDate + ", duration: " + duration +
                ", score: " + score + ')';
    }
}