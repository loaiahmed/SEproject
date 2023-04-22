import java.util.LinkedList;

public class Head extends Staff {
    public Head() {
        super();
    }

    public Head(String name, String phoneNumber, Account account) {
        super(name, phoneNumber, account);
    }

    public Head(double salary, String experience, String certificates) {
        super(salary, experience, certificates);
    }


    public Head(String name, String phoneNumber, Account account, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, salary, experience, certificates);
    }

    public Head(String name, String phoneNumber, Account account, LinkedList<Course> courses, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses, salary, experience, certificates);
    }

    public Head(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses, attendance, salary, experience, certificates);
    }

    public boolean banStudent(Student st, Course c) {
        if (!(IT.allStudents.contains(st) && IT.allCourses.contains(c))) {
            return false;
        }
        return st.getCourses().remove(c);
    }

    public LinkedList<Student> getCoursesRelatedStudents() {
        LinkedList<Student> students = new LinkedList<>();

        for (Student student : IT.allStudents) {
            for (Course course : getCourses()) {
                if (student.getCourses().contains(course)) {
                    students.add(student);
                    break;
                }
            }
        }
        return students;
    }

    public LinkedList<Student> generateMaxAbsenceList() {
        LinkedList<Student> maxAbsenceList = new LinkedList<>();
        LinkedList<Student> students = getCoursesRelatedStudents();
        for (Student student : students) {
            if (student.getStrike() >= 6) {
                maxAbsenceList.add(student);
            }
        }
        return maxAbsenceList;
    }
}
