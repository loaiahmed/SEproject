
import javax.swing.*;
import java.util.LinkedList;
import java.util.Scanner;

public class IT extends Staff {

    static LinkedList<Student> allStudents = new LinkedList<>();
    static LinkedList<Course> allCourses = new LinkedList<>();
    static LinkedList<Teacher> allTeachers = new LinkedList<>();
    static LinkedList<Head> allHeads = new LinkedList<>();

    public IT() {
        super();
    }

    public IT(double salary, String experience, String certificates) {
        super(salary, experience, certificates);
    }

    public IT(String name, String phoneNumber, Account account, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, salary, experience, certificates);
    }

//    public IT(String name, String phoneNumber, Account account, LinkedList<Attendance> attendance, double salary, String experience, String certificates) {
//        super(name, phoneNumber, account, attendance, salary, experience, certificates);
//    }

    public void addTeacher(Teacher t){
        allTeachers.add(t);
    }

    public void removeTeacher(Teacher t){
        if(!(allTeachers.contains(t))){
            System.out.println("Teacher not found");
        }
        allTeachers.remove(t);
    }
    public void addHead(Head h){
        allHeads.add(h);
    }

    public void removeHead(Head head){
        if(!(allHeads.contains(head))){
            System.out.println("Head not found");
        }
        allHeads.remove(head);
    }

    public void assignHeadToCourse(Head head,Course c){
        head.getCourses().add(c);
        c.setCourseDoctor(head);
    }

    public void unassignHeadToCourse(Course c){
        c.getCourseDoctor().getCourses().remove(c);
        c.setCourseDoctor(null);
    }
    public boolean unassignHeadToCourse(Head head, Course c){
        if(!(allHeads.contains(head))){
            System.out.println("Head not found");
            return false;
        }
        if(!head.getCourses().remove(c)) {
            System.out.println("course is not in head");
            return false;
        }
        c.setCourseDoctor(null);
        return true;
    }
    public void assignTeacherToCourse(Teacher teacher,Course c){
        teacher.getCourses().add(c);
    }
    public boolean unassignTeacherToCourse(Teacher teacher, Course c){
        return teacher.getCourses().remove(c);
    }
    void addCourse(Course c){
        allCourses.add(c);
    }

    void removeCourse(Course c){
        if(!(allCourses.contains(c))){
            System.out.println("Course not found");
        }
        else{
            c.getCourseDoctor().getCourses().remove(c);
            allCourses.remove(c);
        }
    }

    void addStudent(Student s){
        allStudents.add(s);
    }

    void removeStudent(Student s){
        if(!(allStudents.contains(s))){
            System.out.println("Student not found");
        }
        else{
            allStudents.remove(s.getUserID());
        }
    }

    public boolean enrollStudentToCourse(Student student, Course course){
        if(!(allStudents.contains(student))){
            System.out.println(student);
            System.out.println("student not in data");
            return false;
        }
        if(!(allCourses.contains(course))){
            System.out.println("course not in data");
            return false;
        }
        student.getCourses().add(course);
        return true;
    }
    public boolean removeStudentFromCourse(Student student, Course course){
        return student.getCourses().remove(course);
    }

    public boolean isEnrolledToCourse(Student student, Course course){
        return allStudents.contains(student) && allCourses.contains(course);
    }

    public static LinkedList<Student> getAllStudents() {
        return allStudents;
    }

    public static void setAllStudents(LinkedList<Student> allStudents) {
        IT.allStudents = allStudents;
    }

    public static LinkedList<Course> getAllCourses() {
        return allCourses;
    }

    public static void setAllCourses(LinkedList<Course> allCourses) {
        IT.allCourses = allCourses;
    }

    public static Student getStudentWithAccount(Account account){
        for( Student student : allStudents){
            if(student.getAccount().equals(account)){
                return student;
            }
        }
        System.out.println("couldn't find student with corresponding account");
        return null;
    }
    public static Student getStudentWithAccount(String emailAddress, String password){
        for( Student student : allStudents){
            if(student.getAccount().getEmailAddress().equals(emailAddress) && student.getAccount().getPassword().equals(password)){
                return student;
            }
        }
        System.out.println("couldn't find student with corresponding account");
        return null;
    }
    public static boolean isStudentWithAccount(String emailAddress, String password){
        for( Student student : allStudents){
            if(student.getAccount().getEmailAddress().equals(emailAddress) && student.getAccount().getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public static boolean isHeadWithAccount(String emailAddress, String password){
        for( Teacher teacher : allTeachers){
            if(teacher.getAccount().getEmailAddress().equals(emailAddress) && teacher.getAccount().getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public static boolean isTeacherWithAccount(String emailAddress, String password){
        for( Teacher teacher : allTeachers){
            if(teacher.getAccount().getEmailAddress().equals(emailAddress) && teacher.getAccount().getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public boolean manageStudents(Student student, Course course){
        if(!(isEnrolledToCourse(student, course))){
            return false;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enroll to course? or remove from course?: 0, 1");
        int choice = input.nextInt();

        switch (choice) {
            case 0 -> enrollStudentToCourse(student, course);
            case 1 -> removeStudentFromCourse(student, course);
            default -> {
                System.out.println("undefined input!! terminating function");
                return false;
            }
        }

        return true;
    }

    public LinkedList<Student> getUnAssignedStudents(){
        LinkedList<Student> students = new LinkedList<>();
        for(Student student : allStudents){
            if(student.getCourses().isEmpty()){
                students.add(student);
            }
        }
        return students;
    }
    public LinkedList<Student> getAssignedStudents(){
        LinkedList<Student> students = new LinkedList<>();
        for(Student student : allStudents){
            if(!(student.getCourses().isEmpty())){
                students.add(student);
            }
        }
        return students;
    }
    public LinkedList<Student> getStudentsWithCourse(Course course){
        LinkedList<Student> students = new LinkedList<>();
        for(Student student : allStudents){
            if(student.getCourses().contains(course)){
                students.add(student);
            }
        }
        return students;
    }

    public LinkedList<Head> getUnAssignedHeads(){
        LinkedList<Head> heads = new LinkedList<>();
        for(Head head : allHeads){
            if(head.getCourses().isEmpty()){
                heads.add(head);
            }
        }
        return heads;
    }
    public LinkedList<Head> getAssignedHeads(){
        LinkedList<Head> heads = new LinkedList<>();
        for(Head head : allHeads){
            if(!(head.getCourses().isEmpty())){
                heads.add(head);
            }
        }
        return heads;
    }
    public LinkedList<Head> getHeadWithCourse(Course course){
        LinkedList<Head> heads = new LinkedList<>();
        for(Head head : allHeads){
            if(head.getCourses().contains(course)){
                heads.add(head);
            }
        }
        return heads;
    }

    public LinkedList<Teacher> getUnAssignedTeacher(){
        LinkedList<Teacher> teachers = new LinkedList<>();
        for(Teacher teacher : allTeachers){
            if(teacher.getCourses().isEmpty()){
                teachers.add(teacher);
            }
        }
        return teachers;
    }
    public LinkedList<Teacher> getAssignedTeacher(){
        LinkedList<Teacher> teachers = new LinkedList<>();
        for(Teacher teacher : allTeachers){
            if(!(teacher.getCourses().isEmpty())){
                teachers.add(teacher);
            }
        }
        return teachers;
    }
    public LinkedList<Teacher> getTeacherWithCourse(Course course){
        LinkedList<Teacher> teachers = new LinkedList<>();
        for(Teacher teacher : allTeachers){
            if(teacher.getCourses().contains(course)){
                teachers.add(teacher);
            }
        }
        return teachers;
    }
}
