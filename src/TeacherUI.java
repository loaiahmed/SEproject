import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class TeacherUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JTextPane welcomeTeacherTextPane;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton updateButton;
    private JButton takeAttendanceButton;
    private JTable complaintsTable;
    private JButton editAttendanceButton;
    private JTextField textField1;
    private JTable searchTable;
    private JButton searchButton;
    private JButton editAttendanceButton1;

    private final Teacher teacher;
    TeacherUI(Teacher teacher){
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Teacher UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.teacher = teacher;


        createComboBox(comboBox1);
        createStudentsTable(teacher.getCoursesRelatedStudents());
        createComplaintsTable(Teacher.complaints);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(table1);
                createStudentsTable(teacher.getCoursesRelatedStudents());
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem() == "All Courses"){
                    clearTable(table1);
                    createStudentsTable(teacher.getCoursesRelatedStudents());
                }
                else {
                    clearTable(table1);
                    Course course = (Course) comboBox1.getSelectedItem();
                    System.out.println(course);
                    createStudentsTable(teacher.getCourseRelatedStudents(course));
                }
            }
        });
        takeAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem() == "All Courses"){
                    ManageAttendance manageAttendance = new ManageAttendance(teacher.getCoursesRelatedStudents());
                }
                else {
                    Course course = (Course) comboBox1.getSelectedItem();
                    System.out.println(course);
                    ManageAttendance manageAttendance = new ManageAttendance(teacher.getCourseRelatedStudents(course));
                }
            }
        });
        editAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Complaint complaint = (Complaint) getSelectedRow(complaintsTable);
                new attendanceEditor(complaint.getAttendance());
            }
        });
        searchButton.addActionListener(new ActionListener() {       // horrible search implementation needs improvement
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Student> teacherStudents = teacher.getCoursesRelatedStudents();

                for (Student student : teacherStudents){
                    if (Integer.toString(student.getUserID()).equals(textField1.getText())){
                        createSearchTable(student);
                        return;
                    }
                }
            }
        });
        editAttendanceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new attendanceEditor((Attendance) getSelectedRow(searchTable));
            }
        });
    }



    public void createComboBox(JComboBox comboBox1){
        comboBox1.addItem("All Courses");
        Course[] userCourses = teacher.getCourses().toArray(new Course[0]);
        for (Course userCourse : userCourses) {
            comboBox1.addItem(userCourse);
        }
    }
    public Object getSelectedRow(JTable table){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        Vector<Vector> data = dm.getDataVector();
        for(int i = 0; i < data.size(); i++){
            if(i == table.getSelectedRow()) {
                return data.get(i).lastElement();
            }
        }
        return null;
    }
    public void clearTable(JTable table){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }

    public void createStudentsTable(LinkedList<Student> students){
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
    public void createComplaintsTable(LinkedList<Complaint> complaints){
        Object[][] data = new Object[complaints.size()][3];

        for(int j = 0; j < complaints.size(); j++){
            data[j][0] = complaints.get(j).getComplaintText();
            data[j][1] = complaints.get(j).getAttendance();
            data[j][2] = complaints.get(j);
        }
        complaintsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Complaint Text", "Attendance", "Complaint"}
        ));
        complaintsTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        complaintsTable.getColumnModel().getColumn(2).setMaxWidth(0);
        complaintsTable.getColumnModel().getColumn(2).setMinWidth(0);
    }
    public void createSearchTable(Student student){
        Object[][] data = new Object[student.getAttendance().size()][6];

        for(int j = 0; j < student.getAttendance().size(); j++){
            data[j][0] = student.getAttendance().get(j).getAttendanceID();
            data[j][1] = student.getAttendance().get(j).getDateOfAttendance();
            data[j][2] = student.getAttendance().get(j).getStudent().getUserID();
            data[j][3] = student.getAttendance().get(j).getStudent().getName();
            data[j][4] = student.getAttendance().get(j).isPresent();
            data[j][5] = student.getAttendance().get(j);
        }

        DefaultTableModel model = new DefaultTableModel(data, new Object[]{"ID", "date", "student ID", "Student Name", "Present", "Attendance"});
        searchTable.setModel(model);

        searchTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        searchTable.getColumnModel().getColumn(5).setMaxWidth(0);
        searchTable.getColumnModel().getColumn(5).setMinWidth(0);
    }


}
