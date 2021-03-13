/**
 * @author Alex Amarandei
 */

package compulsory;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        List<Student> studentList = new LinkedList<>();
        studentList.addAll(Arrays.asList(students));
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

        System.out.println(studentPreferences);
        System.out.println(schoolPreferences);
    }
}
