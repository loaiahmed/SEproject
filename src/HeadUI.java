import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class HeadUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JTextPane welcomeTextPane;
    private JButton updateListButton;
    private JButton banStudentButton;
    private JTable table1;
    private JComboBox comboBox1;
    private JLabel attendanceRateLabel;

    private final Head head;

    HeadUI(Head head){
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Head UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        append(head.getName()); // appends head name to welcomeTextPane

        this.head = head;

        comboBox1.addItem("All Courses");
        Course[] headCourses = head.getCourses().toArray(new Course[0]);
        for (Course headCourse : headCourses) {
            comboBox1.addItem(headCourse);
        }

//        System.out.println(Arrays.toString(headCourses));

        createTable();
        attendanceRateLabel.setText("Course Overall Attendance Rate = " + String.format("%.4f", head.getStudentsAttendanceRate()));
        banStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(table1.getSelectedRow());
                if(comboBox1.getSelectedItem() == "All Courses"){
                    JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    head.banStudent(getSelectedStudent(), (Course) comboBox1.getSelectedItem());
                    JOptionPane.showMessageDialog(rootPanel, "ban done");
                }
            }
        });
        updateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                createTable();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem() == "All Courses"){
                    clearTable();
                    createTable();
                    attendanceRateLabel.setText("Course Overall Attendance Rate = " + String.format("%.4f", head.getStudentsAttendanceRate()));
                }
                else {
                    clearTable();
                    Course course = (Course) comboBox1.getSelectedItem();
                    System.out.println(course);
                    createTable(course);
                    attendanceRateLabel.setText("Course Overall Attendance Rate = " + String.format("%.4f", head.getStudentsAttendanceRate(course)));
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });
    }

    public void logOut(){
        this.dispose();
        new StartUp();
    }

    public void append(String s) {
        try {
            Document doc = welcomeTextPane.getDocument();
            doc.insertString(doc.getLength(), s, null);
        } catch(BadLocationException exc) {
            exc.printStackTrace();
        }
    }
    public Course getSelectedCourseByName(String courseName){
        for(Course course : head.getCourses()){
            if(course.getCourseName().equals(courseName)){
                return course;
            }
        }
        return null;
    }
    public Student getSelectedStudent(){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();
        Vector<Vector> data = dm.getDataVector();
        for(int i = 0; i < data.size(); i++){
            if(i == table1.getSelectedRow()) {
                return (Student) data.get(i).get(5);
            }
        }
        return null;
    }

    public void clearTable(){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
    public void createTable(){
        LinkedList<Student> students = this.head.getCoursesRelatedStudents();
        Object[][] data = new Object[students.size()][6];

        for(int j = 0; j < students.size(); j++){
            data[j][0] = students.get(j).getUserID();
            data[j][1] = students.get(j).getName();
            data[j][2] = students.get(j).getAccount().getEmailAddress();
            data[j][3] = students.get(j).getGPA();
            data[j][4] = students.get(j).getStrike();
            data[j][5] = students.get(j);
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Email", "GPA", "Number Of Absence", "Student"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
    public void createTable(Course course){
        LinkedList<Student> students = this.head.getCourseRelatedStudents(course);
        System.out.println(this.head.getCourseRelatedStudents(course));
        Object[][] data = new Object[students.size()][6];

        for(int j = 0; j < students.size(); j++){
            data[j][0] = students.get(j).getUserID();
            data[j][1] = students.get(j).getName();
            data[j][2] = students.get(j).getAccount().getEmailAddress();
            data[j][3] = students.get(j).getGPA();
            data[j][4] = students.get(j).getStrike();
            data[j][5] = students.get(j);
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Email", "GPA", "Number Of Absence", "Student"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
}
