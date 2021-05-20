import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertEquals;

public class Main {
    public static void main(String[] args) {
        File file;
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            file = new File(url);
        } else {
            file = new File(args[0]);
        }

        try {
            exploreMethods(file);
            explorePackage(file);
            exploreFields(file);
        } catch (MalformedURLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void exploreMethods(File file) throws MalformedURLException, ClassNotFoundException {
        URL[] classPath = {
                file.toURI().toURL()
        };

        URLClassLoader urlClassLoader = new URLClassLoader(classPath);
        Class loadedClass = urlClassLoader.loadClass("dummy.Dummy");

        int successful = 0, unsuccessful = 0;
        for (Method method : loadedClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                    successful++;
                } catch (Throwable exception) {
                    System.out.printf("Test %s failed: %s %n",
                            method, exception.getCause());
                    unsuccessful++;
                }
            }
        }

        System.out.printf("Successful: %d\nUnsuccessful: %d\n", successful, unsuccessful);
    }

    static void explorePackage(File file) throws MalformedURLException, ClassNotFoundException {
        URL[] classPath = {
                file.toURI().toURL()
        };

        URLClassLoader urlClassLoader = new URLClassLoader(classPath);
        Class loadedClass = urlClassLoader.loadClass("dummy.Dummy");
        Package loadedPackage = loadedClass.getPackage();

        assertEquals("dummy", loadedPackage.getName());
    }

    static void exploreFields(File file) throws MalformedURLException, ClassNotFoundException {
        URL[] classPath = {
                file.toURI().toURL()
        };

        URLClassLoader urlClassLoader = new URLClassLoader(classPath);
        Class loadedClass = urlClassLoader.loadClass("dummy.Dummy");
        Package loadedPackage = loadedClass.getPackage();

        assertEquals("dummy", loadedPackage.getName());

        Field[] fields = loadedClass.getDeclaredFields();
        Arrays.stream(fields)
                .forEach(f -> System.out.print("Type: " + f.getAnnotatedType() + "\nName: " + f.getName() + "\n"));
    }
}
