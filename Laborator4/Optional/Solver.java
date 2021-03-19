/**
 * @author Alex Amarandei
 */

package laborator4.optional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {
    static Solution scoreOnlyMatching(Problem problem) {
        Map<Student, School> matching = new HashMap<>();
        Map<Student, List<School>> studentPreferences = problem.getStudentPreferences();
        List<Student> studentList = problem.getStudentList().stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toList());

        for(Student student : studentList) {
            School topSchool = studentPreferences.get(student).stream()
                    .filter(school -> school.getCapacity() > 0)
                    .findFirst()
                    .orElse(null);

            matching.put(student, topSchool);
            topSchool.setCapacity(topSchool.getCapacity() - 1);
        }

        return new Solution(matching);
    }
}
