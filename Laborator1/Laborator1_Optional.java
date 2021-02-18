import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Laborator1_Optional {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

    // Optional Task #1
        if(args.length < 1) System.out.println("You have not provided enough arguments!");
        else {
            String potentialNumber = args[0];
            int n = -1;
            try {
                n = Integer.parseInt(potentialNumber);
                System.out.println(n);
            } catch(NumberFormatException e) {
                System.err.println(e.getMessage());
                System.out.println("Not a valid dimension for matrix.");
            }

            if(n < 1) System.out.println("Not a valid dimension for matrix.");
            else {
                Graph m = new Graph(n);

                if(n <= 20) m.printGraph();

                if(m.isConnected()) {
                    System.out.println("\nThe above graph is connected.");

                // Optional Task #2
                    m.generatePartialTree();
                }
                else System.out.println("\nThe above graph is not connected.");
            }
        }

        // Optional Task #3

        long endTime = System.nanoTime();
        long runningTime = endTime - startTime;

        System.out.println("Running time: " + (runningTime / 1000000) + "ms\n");

        // Optional Task #4 - tested all the optional parts from the terminal
    }
}
