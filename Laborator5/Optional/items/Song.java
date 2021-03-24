/**
 * @author Alex Amarandei
 */

package optional.items;

public class Song extends Item {
    private String artist;

    public Song(String name, String path, String artist) {
        super(name, path);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
