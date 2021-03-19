/**
 * @author Alex Amarandei
 */

package laborator4.optional;

// Optional task #1 - create a class that describes an instance of this problem

import java.util.List;
import java.util.Map;

public class Problem {
    private List<Student> studentList;
    private Map<Student, List<School>> studentPreferences;

    public Problem(List<Student> studentList, Map<Student, List<School>> studentPreferences) {
        this.studentList = studentList;
        this.studentPreferences = studentPreferences;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Map<Student, List<School>> getStudentPreferences() {
        return studentPreferences;
    }

    public void setStudentPreferences(Map<Student, List<School>> studentPreferences) {
        this.studentPreferences = studentPreferences;
    }
}
