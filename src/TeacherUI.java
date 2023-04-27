import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class TeacherUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton button1;
    private JTextPane welcomeTeacherTextPane;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton updateButton;
    private JButton takeAttendanceButton;
    private JTable complaintsTable;
    private JButton editAttendanceButton;
    private JTextField textField1;
    private JTable table3;
    private JComboBox comboBox2;
    private JButton searchButton;
    private JButton saveButton;

    private Teacher teacher;
    TeacherUI(Teacher teacher){
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Head UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.teacher = teacher;

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
        Object[][] data = new Object[complaints.size()][6];

        for(int j = 0; j < complaints.size(); j++){
            data[j][0] = complaints.get(j).getComplaintText();
            data[j][1] = complaints.get(j).getAttendance();
            data[j][2] = complaints.get(j);
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"Complaint Text", "Attendance", "Complaint"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(3).setMaxWidth(0);
        table1.getColumnModel().getColumn(3).setMinWidth(0);
    }


}
