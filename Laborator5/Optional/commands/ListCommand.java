/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidNumberOfArgumentsException;
import optional.exceptions.NoCatalogSelectedException;

public class ListCommand extends Command {
    public ListCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException {
        if (arguments.size() != 1) throw new
                InvalidNumberOfArgumentsException("Too many arguments for List Command, the expected number is 1.");

        if (catalog == null) throw new
                NoCatalogSelectedException("No catalog selected.");

        catalog.list();

        return catalog;
    }
}
