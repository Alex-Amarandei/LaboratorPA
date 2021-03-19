/**
 * @author Alex Amarandei
 */

package laborator4.optional;

// Optional task #1 - create a class that describes a solution (a matching) of this problem

import java.util.Map;

public class Solution {
    Map<Student, School> matching;

    public Solution(Map<Student, School> matching) {
        this.matching = matching;
    }

    @Override
    public String toString() {
        StringBuilder solution = new StringBuilder();
        matching.forEach((student, school) -> solution.append(student + " got into " + school + "\n"));
        return solution.toString();
    }
}
