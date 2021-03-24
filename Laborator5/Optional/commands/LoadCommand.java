/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.CatalogUtil;
import optional.exceptions.CatalogException;
import optional.exceptions.CatalogNotSavedException;
import optional.exceptions.InvalidNumberOfArgumentsException;

import java.io.IOException;

public class LoadCommand extends Command {
    public LoadCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException, IOException {
        if (arguments.size() < 3) throw new
                InvalidNumberOfArgumentsException("Too few arguments for Load Command, the expected number is 3.");

        if (arguments.size() > 3) throw new
                InvalidNumberOfArgumentsException("Too many arguments for Load Command, the expected number is 3.");

        if (catalog != null) throw new
                CatalogNotSavedException("The previous catalog was not saved.");

        catalog = CatalogUtil.load(arguments.get(2));
        catalog.setName(arguments.get(1));

        System.out.println("Loaded " + arguments.get(1));
        return catalog;
    }
}
