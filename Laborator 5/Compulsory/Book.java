/**
 * @author Alex Amarandei
 */

package compulsory;

public class Book extends Item {
    public Book(String name, String path) {
        super(name, path);
    }

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
