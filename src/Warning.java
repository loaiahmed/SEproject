import java.util.Objects;

public class Warning {
    private final int warningID;
//    private Student directedTo;
    static int count = 0;


    public Warning() {
        this.warningID = count;
        count++;
    }

    public int getWarningID() {
        return warningID;
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
