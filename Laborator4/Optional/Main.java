/**
 * @author Alex Amarandei
 */

package laborator4.optional;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Optional Task #3 - generate random fake names for students and schools

        Faker faker = new Faker();

        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toArray(Student[]::new);

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School(faker.university().name()))
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

        // Optional Task #2 - queries that display the students who find acceptable a given list of schools, and the schools that have a given student as their top preference

        List<School> exampleSchools = new ArrayList<>() {{
            add(schools[0]);
            add(schools[2]);
        }};

        Student exampleStudent = students[0];

        System.out.print("\nThe students who find ");
        exampleSchools.forEach(school -> System.out.print(school + ", "));
        System.out.print("acceptable are:\n");

        studentList.stream()
                .filter(student -> studentPreferences.get(student).containsAll(exampleSchools))
                .forEach(System.out::println);

        System.out.print("\nThe schools who find " + exampleStudent + " as their top choice are:\n");

        schoolSet.stream()
                .filter(school -> schoolPreferences.get(school).get(0).equals(exampleStudent))
                .forEach(System.out::println);

        // Optional Task #4 - consider that each student has a score

        studentList.forEach(student -> student.setScore((int) (Math.random() * 100)));

        System.out.println("\nThe students' scores are:");
        studentList.forEach(student -> System.out.println(student + ": " + student.getScore()));

        // Optional Task #4 - implement an algorithm for creating a matching, considering that the schools rank students based on this score

        Problem sampleProblem = new Problem(studentList, studentPreferences);
        Solution sampleSolution = Solver.scoreOnlyMatching(sampleProblem);
        System.out.println();
        System.out.println(sampleSolution);
    }
}
