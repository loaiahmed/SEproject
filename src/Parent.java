import java.util.LinkedList;
import java.util.Objects;

public class Parent {

    private final int ID;
    private String name;
    private String email;
    private String phoneNumber;
    private LinkedList<Warning> receivedWarnings;
    static int count = 0;

    public Parent(String name, String email, String phoneNumber) {
        this.ID = count;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        count++;
    }

    public Parent() {
        this.ID = count;
        count++;
    }

    public void addWarning(Warning w) {
        receivedWarnings.add(w);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return ID == parent.ID && Objects.equals(name, parent.name) && Objects.equals(email, parent.email) && Objects.equals(phoneNumber, parent.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Parent{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
