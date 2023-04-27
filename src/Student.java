import java.util.LinkedList;
import java.util.Objects;

public class Student extends User {
    private double GPA;
    private LinkedList<Parent> parents;
    private LinkedList<Warning> warnings;
    private int strike = 0;

    public Student() {
    }

    public Student(String name, String phoneNumber, Account account) {
        super(name, phoneNumber, account);

        this.parents = new LinkedList<>();
        this.warnings = new LinkedList<>();
    }

    public Student(String name, String phoneNumber, Account account, double GPA, LinkedList<Parent> parents) {
        super(name, phoneNumber, account);
        this.GPA = GPA;
        this.parents = parents;
        this.warnings = new LinkedList<>();
    }

    public Student(String name, String phoneNumber, Account account, LinkedList<Course> courses, double GPA, LinkedList<Parent> parents, LinkedList<Warning> warnings) {
        super(name, phoneNumber, account, courses);
        this.GPA = GPA;
        this.parents = parents;
        this.warnings = warnings;
    }

    public Student(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance, double GPA) {
        super(name, phoneNumber, account, courses, attendance);
        this.GPA = GPA;
        this.warnings = new LinkedList<>();
    }

    public Student(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance, double GPA, LinkedList<Parent> parents, LinkedList<Warning> warnings) {
        super(name, phoneNumber, account, courses, attendance);
        this.GPA = GPA;
        this.parents = parents;
        this.warnings = warnings;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public LinkedList<Parent> getParents() {
        return parents;
    }

    public void setParents(LinkedList<Parent> parents) {
        this.parents = parents;
    }

    public LinkedList<Warning> getWarnings() {
        return warnings;
    }

    public void setWarnings(LinkedList<Warning> warnings) {
        this.warnings = warnings;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }
    public void addToAttendance(Attendance attend) {
        getAttendance().add(attend);
        if (!attend.isPresent()) {
            strike++;
        }
    }
    public void showAttendanceRecord(){
        for(Attendance attend : getAttendance()){
            System.out.println(attend);
        }
    }

    public LinkedList<Attendance> getCourseRelatedAttendance(Course course){
        LinkedList<Attendance> attendances = new LinkedList<>();
        for(Attendance attendance : getAttendance()){
            if(attendance.getCourse().equals(course)){
                attendances.add(attendance);
            }
        }
        return attendances;
    }

    public void submitComplaint(Complaint complaint) {
        Teacher.complaints.add(complaint);
        System.out.println("Complaint submitted.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(student.GPA, GPA) == 0 && strike == student.strike && Objects.equals(parents, student.parents) && Objects.equals(warnings, student.warnings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), GPA, parents, warnings, strike);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name= " + getName() +
                "GPA=" + GPA +
                ", parents=" + parents +
                ", warnings=" + warnings +
                ", strike=" + strike +
                '}';
    }

}
