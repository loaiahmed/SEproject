import java.util.LinkedList;
import java.util.Objects;

public class User {
    private String Name;
    private final int userID;
    private String phoneNumber;
    private Account account;
    private LinkedList<Course> courses;
    private LinkedList<Attendance> attendance;

    static int count = 0;

    public User() {
        this.userID = count;
        this.attendance = new LinkedList<>();
        this.courses = new LinkedList<>();
        count++;
    }

    public User(String name, String phoneNumber, Account account) {
        this.userID = count;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.account = account;
//        Account.addToAccounts(account);
        this.attendance = new LinkedList<>();
        this.courses = new LinkedList<>();
        count++;
    }
    public User(String name, String phoneNumber, Account account, LinkedList<Course> courses) {
        this.userID = count;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.account = account;
//        Account.addToAccounts(account);
        this.courses = courses;
        this.attendance = new LinkedList<>();
        count++;
    }

    public User(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance) {
        this.Name = name;
        this.userID = count;
        this.phoneNumber = phoneNumber;
        this.account = account;
//        Account.addToAccounts(account);
        this.courses = courses;
        this.attendance = attendance;
        count++;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUserID() {
        return userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
//        Account.addToAccounts(account);
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }

    public void setCourses(LinkedList<Course> courses) {
        this.courses = courses;
    }

    public LinkedList<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(LinkedList<Attendance> attendance) {
        this.attendance = attendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(Name, user.Name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(account, user.account) && Objects.equals(courses, user.courses) && Objects.equals(attendance, user.attendance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, userID, phoneNumber, account, courses, attendance);
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", userID=" + userID +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", account=" + account +
                ", courses=" + courses +
                ", attendance=" + attendance +
                '}';
    }
}
