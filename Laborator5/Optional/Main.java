/**
 * @author Alex Amarandei
 */

package optional;

import optional.commands.*;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidShellCommandException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runShell();
    }

    static void runShell() {
        Scanner scanner = new Scanner(System.in);
        String commandLine;
        String commandWord;
        Catalog currentCatalog = null;
        Command command;

        while (true) {
            commandLine = scanner.nextLine();
            if (commandLine.contains("~"))
                commandWord = commandLine.substring(0, commandLine.indexOf("~"));
            else commandWord = commandLine;

            try {
                switch (commandWord) {
                    case "add" -> command = new AddCommand(commandLine);
                    case "list" -> command = new ListCommand(commandLine);
                    case "save" -> command = new SaveCommand(commandLine);
                    case "load" -> command = new LoadCommand(commandLine);
                    case "play" -> command = new PlayCommand(commandLine);
                    case "report" -> command = new ReportCommand(commandLine);
                    case "create" -> command = new CreateCommand(commandLine);
                    case "remove" -> command = new RemoveCommand(commandLine);
                    case "exit" -> command = new ExitCommand(commandLine);
                    default -> throw new InvalidShellCommandException("Not a valid command.");
                }

                currentCatalog = command.execute(currentCatalog);

            } catch (CatalogException exception) {
                System.err.println(exception.getMessage());
                exception.printStackTrace();
            } catch (IOException exception) {
                System.err.println("I/O Error, probably a saving/loading problem.");
                exception.printStackTrace();
            } catch (NullPointerException exception) {
                System.err.println("The command is null.");
                exception.printStackTrace();
            }
        }
    }
}
