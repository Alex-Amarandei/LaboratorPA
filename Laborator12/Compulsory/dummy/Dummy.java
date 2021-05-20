package dummy;

import org.testng.annotations.Test;

public class Dummy {
    String name = "John";
    Integer age = 21;

    @Test
    public static void sayHello() {
        System.out.println("Hello World!");
    }

    @Test
    public static int returnOne() {
        System.out.println("Will return 1");
        return 1;
    }

    @Test
    public static int returnParameter(int parameter) {
        return parameter;
    }

    public static void sayHelloWithNoTest() {
        System.out.println("Hello World with no @Test!");
    }

    @Test
    public void sayHelloButNotStatic() {
        System.out.println("Hello not static World!");
    }
}
