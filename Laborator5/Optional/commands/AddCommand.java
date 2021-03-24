/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidItemTypeException;
import optional.exceptions.InvalidNumberOfArgumentsException;
import optional.exceptions.NoCatalogSelectedException;
import optional.items.Book;
import optional.items.Movie;
import optional.items.Song;

public class AddCommand extends Command {
    public AddCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException {
        if (arguments.size() < 5) throw new
                InvalidNumberOfArgumentsException("Too few arguments for Add Command, the expected number is 5.");

        if (arguments.size() > 5) throw new
                InvalidNumberOfArgumentsException("Too many arguments for Add Command, the expected number is 5.");

        if (catalog == null) throw new
                NoCatalogSelectedException("No catalog selected.");

        switch (arguments.get(1)) {
            case "Book" -> {
                var book = new Book(arguments.get(2), arguments.get(4), arguments.get(3));
                catalog.add(book);
            }
            case "Song" -> {
                var song = new Song(arguments.get(2), arguments.get(4), arguments.get(3));
                catalog.add(song);
            }

            case "Movie" -> {
                var movie = new Movie(arguments.get(2), arguments.get(4), arguments.get(3));
                catalog.add(movie);
            }

            default -> throw new
                    InvalidItemTypeException("Invalid item type, could not be added to the catalog");
        }

        System.out.println("Added " + arguments.get(2));
        return catalog;
    }
}