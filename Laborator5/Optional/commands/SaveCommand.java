/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.CatalogUtil;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidNumberOfArgumentsException;
import optional.exceptions.NoCatalogSelectedException;

import java.io.IOException;

public class SaveCommand extends Command {
    public SaveCommand(String commandLine) {
        super(commandLine);
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException, IOException {
        if (catalog == null) throw new
                NoCatalogSelectedException("No catalog selected.");

        switch (arguments.size()) {
            case 1 -> CatalogUtil.save(catalog);
            case 2 -> {
                catalog.setPath(arguments.get(1));
                CatalogUtil.save(catalog);
            }
            default -> throw new
                    InvalidNumberOfArgumentsException("Too many arguments for Save Command, the maximum expected number is 2.");
        }

        System.out.println("Saved catalog. No catalog currently selected now");
        return null;
    }
}
