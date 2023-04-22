import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public class Attendance {
    private int attendanceID;
    private LocalDateTime dateOfAttendance;
    private Student student;
    private Course course;
    private boolean present;
    static int count = 0;
    private int warningFlag = 0;

    public Attendance() {
    }
    public Attendance(Student student,Course course, boolean present) {
        this.attendanceID = count;
        this.dateOfAttendance = LocalDateTime.now();
        this.course = course;
        this.student = student;
        this.present = present;
        count++;

        sendWarning();
    }
    public Attendance(LocalDateTime dateOfAttendance, Student student,Course course, boolean present) {
        this.attendanceID = count;
        this.dateOfAttendance = dateOfAttendance;
        this.course = course;
        this.student = student;
        this.present = present;
        count++;

        sendWarning();
    }
    public Attendance(LocalDateTime dateOfAttendance, Student student, boolean present) {
        this.attendanceID = count;
        this.dateOfAttendance = dateOfAttendance;
        this.student = student;
        this.present = present;
        count++;

        sendWarning();
    }

    public Attendance(LocalDateTime dateOfAttendance, Student student) {
        this.attendanceID = count;
        this.dateOfAttendance = dateOfAttendance;
        this.student = student;
        count++;

        sendWarning();
    }

    public Attendance(Student student, boolean present) {
        this.attendanceID = count;
        this.dateOfAttendance = LocalDateTime.now();
        this.present = present;
        this.student = student;
        count++;

        sendWarning();
    }

    public Attendance(LocalDateTime dateOfAttendance, Student student, Course course) {
        this.attendanceID = count;
        this.dateOfAttendance = dateOfAttendance;
        this.student = student;
        this.course = course;
        count++;

        sendWarning();
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public LocalDateTime getDateOfAttendance() {
        return dateOfAttendance;
    }

    public void setDateOfAttendance(LocalDateTime dateOfAttendance) {
        this.dateOfAttendance = dateOfAttendance;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public void sendWarning(){
        if(student.getStrike() == 3 && warningFlag == 0){
            student.getWarnings().add(new Warning());
            warningFlag++;
        }
        else if (student.getStrike() == 5 && warningFlag == 1){
            student.getWarnings().add(new Warning());
            warningFlag++;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendance that)) return false;
        if (!super.equals(o)) return false;
        return getAttendanceID() == that.getAttendanceID() && isPresent() == that.isPresent() && getDateOfAttendance().equals(that.getDateOfAttendance()) && getStudent().equals(that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAttendanceID(), getDateOfAttendance(), getStudent(), isPresent());
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceID=" + attendanceID +
                ", dateOfAttendance=" + dateOfAttendance +
                ", student=" + student +
                ", present=" + present +
                '}';
    }
}
