/**
 * @author Alex Amarandei
 */

package laborator4.bonus;

import java.util.*;

public class Solver {
    // Bonus Task #2 - implement the Gale Shapley algorithm in order to find a stable matching

    static Solution galeShepley(Problem problem) {
        Map<Student, List<School>> studentPreferences = problem.getStudentPreferences();
        Map<School, List<Student>> schoolPreferences = problem.getSchoolPreferences();
        // get the important data to avoid repetitive getter calls

        Deque<Student> students = new ArrayDeque<>(problem.getStudentList());
        /* will hold the yet unassigned students
         * current student goes out through the head and the rejected students go in through the tail
         */

        Map<School, List<Student>> schoolRepartition = new HashMap<>();
        schoolPreferences.keySet().stream()
                .forEach(school -> schoolRepartition
                        .put(school, new ArrayList<Student>()));
        // will remember the current repartitions

        while (!students.isEmpty()) {
            Student student = students.remove();
            List<School> preferredSchools = new ArrayList<>(studentPreferences.get(student));

            for (School school : preferredSchools) {
                if (schoolPreferences.get(school).contains(student)) { // if the student is in his preffered school's list
                    if (school.getCapacity() > 0) { // if there is space left, assign the student
                        schoolRepartition.get(school).add(student);
                        school.setCapacity(school.getCapacity() - 1);
                        break;
                    } else {
                        // check if the current student is "better" than the bottom (according to the school's preferences)
                        Student bottom = schoolPreferences.get(school).stream()
                                .filter(std -> schoolRepartition
                                        .get(school)
                                        .contains(std))
                                .reduce((first, second) -> second)
                                .orElse(null);

                        if (schoolPreferences.get(school).indexOf(student) < schoolPreferences.get(school).indexOf(bottom)) {
                            schoolRepartition.get(school).remove(bottom);
                            schoolRepartition.get(school).add(student);
                            students.addLast(bottom);
                            break;
                        }
                    }
                }
            }
        }

        Map<Student, School> matching = new HashMap<>() {{
            schoolRepartition.forEach((sch, stdList) -> stdList.forEach(s -> put(s, sch)));
        }};

        return new Solution(matching);
    }

    /*static Solution scoreOnlyMatching(Problem problem) {
        Map<Student, School> matching = new HashMap<>();
        Map<Student, List<School>> studentPreferences = problem.getStudentPreferences();
        List<Student> studentList = problem.getStudentList().stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toList());

        for (Student student : studentList) {
            School topSchool = studentPreferences.get(student).stream()
                    .filter(school -> school.getCapacity() > 0)
                    .findFirst()
                    .orElse(null);

            matching.put(student, topSchool);
            topSchool.setCapacity(topSchool.getCapacity() - 1);
        }

        return new Solution(matching);
    }*/
}
