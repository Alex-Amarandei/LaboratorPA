/**
 * @author Alex Amarandei
 */

package laborator5.compulsory;

public class Song extends Item {
    public Song(String name, String path) {
        super(name, path);
    }

    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
