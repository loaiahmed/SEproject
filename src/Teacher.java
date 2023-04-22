import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Teacher extends Staff {
    static LinkedList<Complaint> complaints = new LinkedList<>();


    public Teacher() {
        super();
    }

    public Teacher(String name, String phoneNumber, Account account) {
        super(name, phoneNumber, account);
    }

    public Teacher(double salary, String experience, String certificates) {
        super(salary, experience, certificates);
    }

    public Teacher(String name, String phoneNumber, Account account, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, salary, experience, certificates);
    }
    public Teacher(String name, String phoneNumber, Account account, LinkedList<Course> courses, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses, salary, experience, certificates);
    }

    public Teacher(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses, attendance, salary, experience, certificates);
    }

    double getStudentsAttendanceRate(Course c){

        LinkedList<Student> sts = this.getCourseRelatedStudents(c);

        double totalClasses = 0.0;
        double attendedClasses = 0.0;

        for(Student student: sts){
            for(Attendance attendance : student.getAttendance()){
                totalClasses +=1;
                if(attendance.isPresent()){
                    attendedClasses +=1;
                }
            }
        }
        return attendedClasses/totalClasses;
    }

    void checkAsPresent(Attendance a) {
        a.setPresent(true);
    }

    void checkAsAbsent(Attendance a) {
        a.setPresent(false);
    }

    public Attendance takeAttendance(Student st, Course c) {
        Scanner input = new Scanner(System.in);
        Attendance a = new Attendance(LocalDateTime.now(), st, c);

        do {
            System.out.println("Present 1 | Absent 0");
            int option = input.nextInt();

            if (option == 1) {
                checkAsPresent(a);
                return a;
            } else if (option == 0) {
                checkAsAbsent(a);
                return a;
            } else {
                System.out.println("Invalid input, try again");
            }
        }
        while (true);
    }

    public boolean manageAttendance(Course course, Student st, Attendance attendance) {
        LinkedList<Student> students = getCourseRelatedStudents(course);
        if (!students.contains(st)) {
            System.out.println("student is not in your designation");
            return false;
        }
        st.addToAttendance(attendance);
        return true;
    }
    public boolean manageAttendance(Course course, Student st) {        // takes from user
        LinkedList<Student> students = getCourseRelatedStudents(course);
        if (!students.contains(st)) {
            System.out.println("student is not in your designation");
            return false;
        }
        st.addToAttendance(takeAttendance(st, course));
        return true;
    }
    public boolean manageAttendance(Student st, Attendance attendance) {
        LinkedList<Student> students = getCourseRelatedStudents(attendance.getCourse());
        if (!students.contains(st)) {
            System.out.println("student is not in your designation");
            return false;
        }
        st.addToAttendance(attendance);
        return true;
    }
}
