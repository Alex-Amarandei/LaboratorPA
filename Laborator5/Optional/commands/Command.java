/**
 * @author Alex Amarandei
 */

package optional.commands;

import optional.Catalog;
import optional.exceptions.CatalogException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Command {
    protected String commandLine;
    protected List<String> arguments = new ArrayList<>();

    public Command(String commandLine) {
        this.commandLine = commandLine;
        Collections.addAll(this.arguments, commandLine.split("~"));
    }

    public abstract Catalog execute(Catalog catalog) throws CatalogException, IOException;
}
