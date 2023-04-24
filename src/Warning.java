import java.util.Objects;

public class Warning {
    private final int warningID;
//    private Student directedTo;
    private final Course course;
    private final String warningNumber;
    static int count = 0;


    public Warning(Course course, String warningNumber) {
        this.warningID = count;
        this.course = course;
        this.warningNumber = warningNumber;
        count++;
    }

    public int getWarningID() {
        return warningID;
    }

    public Course getCourse() {
        return course;
    }

    public String  getWarningNumber() {
        return warningNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warning warning = (Warning) o;
        return warningID == warning.warningID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(warningID);
    }

    @Override
    public String toString() {
        return "Warning{" +
                "warningID=" + warningID +
                '}';
    }
}
