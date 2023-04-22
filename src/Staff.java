import java.util.LinkedList;

public class Staff extends User {
    private double salary;
    private String experience;
    private String certificates;
    private LinkedList<Student> Students;

    public Staff() {
        super();
    }

    public Staff(String name, String phoneNumber, Account account) {
        super(name, phoneNumber, account);
    }

    public Staff(double salary, String experience, String certificates) {
        super();
        this.salary = salary;
        this.experience = experience;
        this.certificates = certificates;
    }

    public Staff(String name, String phoneNumber, Account account, double salary, String experience, String certificates) {
        super(name, phoneNumber, account);
        this.salary = salary;
        this.experience = experience;
        this.certificates = certificates;
    }

    public Staff(String name, String phoneNumber, Account account, LinkedList<Course> courses, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses);
        this.salary = salary;
        this.experience = experience;
        this.certificates = certificates;
    }

    public Staff(String name, String phoneNumber, Account account, LinkedList<Course> courses, LinkedList<Attendance> attendance, double salary, String experience, String certificates) {
        super(name, phoneNumber, account, courses, attendance);
        this.salary = salary;
        this.experience = experience;
        this.certificates = certificates;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public LinkedList<Student> getStudents() {
        return Students;
    }

    public void setStudents(LinkedList<Student> Students) {
        this.Students = Students;
    }

    public LinkedList<Student> getCourseRelatedStudents(Course course) {
        LinkedList<Student> students = new LinkedList<>();

        if (!(getCourses().contains(course))) {
            System.out.println("course not found in your designation");
        }
        for (Student student : IT.allStudents) {
            if (student.getCourses().contains(course)) {
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public String toString() {
        return "Staff{" + "salary=" + salary + ", experience=" + experience + ", certificates=" + certificates + ", Students=" + Students + '}';
    }

}
