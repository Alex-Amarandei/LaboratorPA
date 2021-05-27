package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ResourceBundle.Control resourceBundleControl = ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("res/Messages", Locale.getDefault(), classLoader, resourceBundleControl);

        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(resourceBundle.getString("prompt") + " ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("display locales")) {
                System.out.println(resourceBundle.getString("locales"));
                new DisplayLocales().execute();
            } else if (input.equalsIgnoreCase("current locale")) {
                String pattern = resourceBundle.getString("locale.set");
                String message = MessageFormat.format(pattern, Locale.getDefault().toString());
                System.out.println(message);
            } else if (input.equalsIgnoreCase("info")) {
                String pattern = resourceBundle.getString("info");
                String message = MessageFormat.format(pattern, Locale.getDefault().toString());
                System.out.println(message);
                new Info().info(Locale.getDefault());
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else {
                String[] splitInput = input.split(" ");
                if (splitInput[0].equalsIgnoreCase("setlocale")) {
                    new SetLocale().execute(new Locale(splitInput[1], splitInput[2]));
                    resourceBundle = ResourceBundle.getBundle("res/Messages");
                } else {
                    System.out.println(resourceBundle.getString("invalid"));
                }
            }
        }
    }
}
