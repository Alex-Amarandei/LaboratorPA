/**
 * @author Alex Amarandei
 */

package optional.items;

public class Movie extends Item {
    private String genre;

    public Movie(String name, String path, String genre) {
        super(name, path);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
