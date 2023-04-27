import java.util.Objects;

public class Course {
    private int courseID;
    private String courseCode;
    private String courseName;
    private User CourseDoctor;

    static int count = 0;

    public Course() {
    }

    public Course(String courseCode, String courseName) {
        this.courseID = count;
        this.courseCode = courseCode;
        this.courseName = courseName;
        count++;
    }
    public Course(String courseCode, String courseName, User courseDoctor) {
        this.courseID = count;
        this.courseCode = courseCode;
        this.courseName = courseName;
        CourseDoctor = courseDoctor;
        count++;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public User getCourseDoctor() {
        return CourseDoctor;
    }

    public void setCourseDoctor(User courseDoctor) {
        CourseDoctor = courseDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        if (!super.equals(o)) return false;
        return getCourseID() == course.getCourseID() && getCourseCode().equals(course.getCourseCode()) && getCourseName().equals(course.getCourseName()) && getCourseDoctor().equals(course.getCourseDoctor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCourseID(), getCourseCode(), getCourseName(), getCourseDoctor());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
//                ", CourseDoctor=" + CourseDoctor +        //removed it because it would cause an error... it would cause a loop with .toString to user and then again toString to cause and so on
                '}';
    }
}
