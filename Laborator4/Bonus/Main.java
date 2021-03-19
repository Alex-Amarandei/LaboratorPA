/**
 * @author Alex Amarandei
 */

package laborator4.bonus;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        /* Bonus Task #1 - consider the case in which a school can rank the students based on their specific criteria
         * e.g. the example on the Advanced Programming website
         */

        testGaleShepleyWithExample();
        /* Bonus Task #3 - consider the case in which preferences are not necessarily strict
         * if we apply the algorithm for S0: H0, H1, H2 and then for S0: H0, H2, H1, it's like having a tied [H1, H2] pair
         * the same goes for applying it for S3: H0, H2 and then for S3: H2, H0
         * The same instance thus has multiple solutions, not necessarily of the same size, as it's reflected in the output
         */

        testGaleShepleyWithSwitchedH1H2();
        testGaleShepleyWithSwitchedH0H2();
    }

    static void testGaleShepleyWithExample() {
        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);

        List<Student> studentList = new LinkedList<>(Arrays.asList(students));
        studentList.sort(Comparator.comparing(Student::getName));

        Set<School> schoolSet = new TreeSet<>(Arrays.asList(schools));

        Map<Student, List<School>> studentPreferences = new HashMap<>() {{
            put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
            put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
            put(students[2], Arrays.asList(schools[0], schools[1]));
            put(students[3], Arrays.asList(schools[0], schools[2]));
        }};

        Map<School, List<Student>> schoolPreferences = new TreeMap<>() {{
            put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
            put(schools[1], Arrays.asList(students[0], students[2], students[1]));
            put(schools[2], Arrays.asList(students[0], students[1], students[3]));
        }};

        // Prints regarding students and schools

        System.out.println("\nThe students are:");
        studentList.forEach(System.out::println);

        System.out.println("\nThe high schools are:");
        schoolSet.forEach(System.out::println);

        System.out.println("\nThe students' preferences are:");
        System.out.println(studentPreferences);

        System.out.println("\nThe high schools' preferences are:");
        System.out.println(schoolPreferences);

        Problem sampleProblem = new Problem(studentList, studentPreferences);
        sampleProblem.setSchoolPreferences(schoolPreferences);
        Solution sampleSolution = Solver.galeShepley(sampleProblem);
        System.out.println();
        System.out.println(sampleSolution);
    }

    static void testGaleShepleyWithSwitchedH1H2() {
        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);

        List<Student> studentList = new LinkedList<>(Arrays.asList(students));
        studentList.sort(Comparator.comparing(Student::getName));

        Map<Student, List<School>> studentPreferences = new HashMap<>() {{
            put(students[0], Arrays.asList(schools[0], schools[2], schools[1]));
            put(students[1], Arrays.asList(schools[0], schools[2], schools[1]));
            put(students[2], Arrays.asList(schools[0], schools[1]));
            put(students[3], Arrays.asList(schools[0], schools[2]));
        }};

        Map<School, List<Student>> schoolPreferences = new TreeMap<>() {{
            put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
            put(schools[1], Arrays.asList(students[0], students[2], students[1]));
            put(schools[2], Arrays.asList(students[0], students[1], students[3]));
        }};

        System.out.println("\nThe same instance, but with [H1, H2] in S0, S1 considered tied and switched for this example.");
        System.out.println("\nThe students' preferences are:");
        System.out.println(studentPreferences);

        Problem sampleProblem = new Problem(studentList, studentPreferences);
        sampleProblem.setSchoolPreferences(schoolPreferences);
        Solution sampleSolution = Solver.galeShepley(sampleProblem);
        System.out.println();
        System.out.println(sampleSolution);
    }

    static void testGaleShepleyWithSwitchedH0H2() {
        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);

        List<Student> studentList = new LinkedList<>(Arrays.asList(students));
        studentList.sort(Comparator.comparing(Student::getName));

        Map<Student, List<School>> studentPreferences = new HashMap<>() {{
            put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
            put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
            put(students[2], Arrays.asList(schools[0], schools[1]));
            put(students[3], Arrays.asList(schools[2], schools[0]));
        }};

        Map<School, List<Student>> schoolPreferences = new TreeMap<>() {{
            put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
            put(schools[1], Arrays.asList(students[0], students[2], students[1]));
            put(schools[2], Arrays.asList(students[0], students[1], students[3]));
        }};

        System.out.println("\nThe same instance, but with [H0, H2] in S3 considered tied and switched for this example.");
        System.out.println("\nThe students' preferences are:");
        System.out.println(studentPreferences);

        Problem sampleProblem = new Problem(studentList, studentPreferences);
        sampleProblem.setSchoolPreferences(schoolPreferences);
        Solution sampleSolution = Solver.galeShepley(sampleProblem);
        System.out.println();
        System.out.println(sampleSolution);
    }
}
