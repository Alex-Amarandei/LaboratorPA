/**
 * @author Alex Amarandei
 */

package optional.items;

public class Book extends Item {
    private String author;

    public Book(String name, String path, String author) {
        super(name, path);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
