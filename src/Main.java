import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        IT it = new IT("Loay", "01146980470", new Account("loay@bue.edu.eg", "Loay2hmed"), 10000, "experience", "certificates");

        Head head = new Head("ahmed", "01146980470", new Account("Ahmed@bue.edu.eg", "2hmed"), 20000, "experience", "certificates");

        Teacher teacher = new Teacher("patrick", "01146980470", new Account("patrick@bue.edu.eg", "Patrickk"), 15000, "experience", "certificates");

        for (int i = 0; i < 10; i++) {
            it.addStudent(new Student("sherouk", "01146980470", new Account("Sherouk@bue.edu.eg", "password"), 0.2 * i, new LinkedList<Parent>()));
        }
        for (int i = 0; i < 5; i++) {
            it.addCourse(new Course("100222", "Math", head));
        }
        head.getCourses().add(IT.getAllCourses().get(0));
        teacher.getCourses().add(IT.getAllCourses().get(0));

        for (int i = 0; i < 10; i++) {
            it.enrollStudentToCourse(IT.getAllStudents().get(i), IT.getAllCourses().get(0));
        }
        LinkedList<Student> students = head.getCourseRelatedStudents(IT.allCourses.get(0));

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                teacher.manageAttendance(teacher.getCourses().get(0), students.get(i), new Attendance(students.get(i), true));
            } else {
                teacher.manageAttendance(teacher.getCourses().get(0), students.get(i), new Attendance(students.get(i), false));
            }
        }
        System.out.println(teacher.getStudentsAttendanceRate(teacher.getCourses().get(0)));

        for (int i = 0; i < 7; i++) {
            teacher.manageAttendance(teacher.getCourses().get(0), students.get(0), new Attendance(students.get(0), false));
        }
        System.out.println(students.get(0));

        LinkedList<Student> blackStudents = head.generateMaxAbsenceList();

        System.out.println(blackStudents);

        IT.allStudents.get(1).showAttendanceRecord();
//        IT.allStudents.get(1).submitComplaint();

        if (head.getCourseRelatedStudents(head.getCourses().get(0)).contains(blackStudents.get(0))) {
            System.out.println("black student exits in course");
        }

        head.banStudent(blackStudents.get(0), head.getCourses().get(0));
        if (!head.getCourseRelatedStudents(head.getCourses().get(0)).contains(blackStudents.get(0))) {
            System.out.println("black student doesn't exits in course");
        }

        Student student1 = IT.allStudents.get(0);
        student1.submitComplaint(new Complaint("i have a problem with your systemmm", student1.getAttendance().get(0)));

        System.out.println();
        System.out.println(Teacher.complaints);

        IT.allStudents.get(1).showAttendanceRecord();

        System.out.println("\n Student Name = " + IT.allStudents.get(0).getName());
        System.out.println("\n Student class = " + IT.allStudents.get(0).getName().getClass());



        it.addCourse(new Course("100222", "English", head));
        it.assignHeadToCourse(head, IT.getAllCourses().getLast());
        Student loai = new Student("Loay Ahmed", "011234213", new Account("loaiSalem@bue.edu.eg", "hello world"));
        it.addStudent(loai);
        it.enrollStudentToCourse(loai, IT.getAllCourses().getLast());
        System.out.println(head.getCourses());

        System.out.println(student1.getWarnings());
        System.out.println(student1.getAttendance());

        it.assignTeacherToCourse(teacher, IT.getAllCourses().getLast());
        for(int i = 0; i < 6; i++) {
            teacher.manageAttendance(loai, new Attendance(loai, teacher.getCourses().getLast(), false));
        }
        System.out.println(loai.getWarnings());



        System.out.println("\n");

        System.out.println("\n" + IT.allStudents);

        System.out.println("\n");
        for(Student student : IT.allStudents){
            System.out.println( student.getAttendance().size() + " " + student.getStrike());
        }
        System.out.println("\n");

//        ManageAttendance manageAttendance = new ManageAttendance(IT.allStudents);

        new TeacherUI(teacher);

//        ITUI itui = new ITUI(it);
//        StudentUI studentUI = new StudentUI(loai);

//        HeadUI headUI = new HeadUI(head);

//        System.out.println("hello world: " + head.getCoursesRelatedStudents());
//        StartUp startUp = new StartUp();
    }
}