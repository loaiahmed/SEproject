import java.util.LinkedList;

public class Staff extends User {
    private double salary;
    private String experience;
    private String certificates;

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

    public LinkedList<Attendance> getCourseRelatedAttendance(Course c, int recordNo) {
        LinkedList<Student> students = new LinkedList<>();
        LinkedList<Attendance> instanceOfAttendance = new LinkedList<>();

        students = getCourseRelatedStudents(c);
        for(Student student: students){
            instanceOfAttendance.add(student.showSpecificAttendanceRecord(recordNo));
        }
        return instanceOfAttendance;
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


    public LinkedList<Student> getCoursesRelatedStudents() {
        LinkedList<Student> students = new LinkedList<>();

        for (Student student : IT.allStudents) {
            for (Course course : this.getCourses()) {
                if (student.getCourses().contains(course)) {
                    students.add(student);
                    break;
                }
            }
        }
        return students;
    }

    public double getStudentsAttendanceRate(Course c){

        LinkedList<Student> sts = this.getCourseRelatedStudents(c);

        double totalClasses = 0.0;
        double attendedClasses = 0.0;

        for(Student student: sts){
            for(Attendance attendance : student.getAttendance()){
                totalClasses +=1;
                if(attendance.isPresent()){
                    attendedClasses +=1;
                }
            }
        }
        return attendedClasses/totalClasses;
    }
    public double getStudentsAttendanceRate(){

        LinkedList<Student> sts = this.getCoursesRelatedStudents();

        double totalClasses = 0.0;
        double attendedClasses = 0.0;

        for(Student student: sts){
            for(Attendance attendance : student.getAttendance()){
                totalClasses +=1;
                if(attendance.isPresent()){
                    attendedClasses +=1;
                }
            }
        }
        return attendedClasses/totalClasses;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "salary=" + salary +
                ", experience='" + experience + '\'' +
                ", certificates='" + certificates + '\'' +
                '}';
    }
}
