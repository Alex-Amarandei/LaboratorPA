/**
 * @author Alex Amarandei
 */

package laborator4.bonus;

import java.util.List;
import java.util.Map;

public class Problem {
    private List<Student> studentList;
    private Map<Student, List<School>> studentPreferences;
    private Map<School, List<Student>> schoolPreferences;

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

    public Map<School, List<Student>> getSchoolPreferences() {
        return schoolPreferences;
    }

    public void setSchoolPreferences(Map<School, List<Student>> schoolPreferences) {
        this.schoolPreferences = schoolPreferences;
    }
}
