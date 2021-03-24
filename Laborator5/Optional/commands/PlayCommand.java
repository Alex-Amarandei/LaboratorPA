/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidNumberOfArgumentsException;
import optional.exceptions.ItemNotFoundException;
import optional.exceptions.NoCatalogSelectedException;

public class PlayCommand extends Command {
    public PlayCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException {
        if (arguments.size() < 2) throw new
                InvalidNumberOfArgumentsException("Too few arguments for Play Command, the expected number is 2.");

        if (arguments.size() > 2) throw new
                InvalidNumberOfArgumentsException("Too many arguments for Play Command, the expected number is 2.");

        if (catalog == null) throw new
                NoCatalogSelectedException("No catalog selected.");

        if (catalog.findByName(arguments.get(1)) == null) throw new
                ItemNotFoundException("There is no item with that name.");

        catalog.play(catalog.findByName(arguments.get(1)));

        return catalog;
    }
}
