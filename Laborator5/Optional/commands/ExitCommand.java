/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.exceptions.CatalogException;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException, IOException {
        System.out.println("Exiting...");
        System.exit(0);
        return null;
    }
}
