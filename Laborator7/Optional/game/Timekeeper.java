/**
 * @author Alex Amarandei
 */

package optional.game;

public class Timekeeper extends Thread {
    private static long TIME_LIMIT = 300;

    @Override
    public void run() {
        long seconds = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            seconds++;
        } while (seconds < TIME_LIMIT);
        System.err.println("\n");
        System.err.println("The " + TIME_LIMIT + " seconds have passed.");
        System.exit(1);
    }

}