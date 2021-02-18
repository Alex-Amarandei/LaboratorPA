package lab1Package;

public class Laborator1 {
    // author: Amarandei Matei Alexandru

    public static void main(String[] args) {
    // Mandatory Task #1
        System.out.println("Hello World!");

    // Mandatory Task #2
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    // Mandatory Task #3
        int n = (int) (Math.random() * 1_000_000);

    // Mandatory Task #4
        int task4Result = ((n * 3) + 0b10101 + 0xFF) * 6;
        System.out.printf("The result of task #4 is %d.\n", task4Result);

    // Mandatory Task #5
        int task5Result;

        // Solution #1
            if(task4Result == 0) task5Result = 0;
            else if(task4Result % 9 == 0) task5Result = 9;
            else task5Result = task4Result % 9;

        // Solution #2 - iterative
        /*
         *   do {
         *      task5Result = 0;
         *      while(task4Result != 0) {
         *          task5Result += task4Result % 10;
         *          task4Result = task4Result / 10;
         *      }
         *      if(task5Result > 10)
         *          task4Result = task5Result;
         *  } while(task4Result != 0);
        */

        System.out.printf("The result of task #5 is %d.\n", task5Result);
        // the result will always be 9 as the result will always be a multiple of 9

    // Mandatory Task #6
        System.out.println("Willy-nilly, this semester I will learn " + languages[task5Result]);
    }
}
