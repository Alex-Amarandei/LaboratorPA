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

public class CreateCommand extends Command {
    public CreateCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException, IOException {
        if (arguments.size() < 3) throw new
                InvalidNumberOfArgumentsException("Too few arguments for Create Command, the expected number is 3.");

        if (arguments.size() > 3) throw new
                InvalidNumberOfArgumentsException("Too many arguments for Create Command, the expected number is 5.");

        if (catalog != null) throw new
                CatalogNotSavedException("The previous catalog was not saved.");

        catalog = new Catalog(arguments.get(1), arguments.get(2));
        CatalogUtil.save(catalog);

        System.out.println("Created " + arguments.get(1));
        return catalog;
    }
}
