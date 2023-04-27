import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class StudentUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JTextPane welcomeTextPane;
    private JTable attendanceTable;
    private JComboBox comboBox1;
    private JButton submitComplaintButton;
    private JButton updateButton;
    private JTable warningsTable;

    private final Student student;

    StudentUI(Student student) {
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Student UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.student = student;

        append(student.getName());

        comboBox1.addItem("All Courses");
        Course[] studentCourses = student.getCourses().toArray(new Course[0]);
        for (Course studentCourse : studentCourses) {
            comboBox1.addItem(studentCourse);
        }

        createAttendanceTable();
        createWarningTable();

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAttendanceTable();
                createAttendanceTable();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem() == "All Courses"){
                    clearAttendanceTable();
                    createAttendanceTable();
                }
                else {
                    clearAttendanceTable();
                    Course course = (Course) comboBox1.getSelectedItem();
                    System.out.println(course);
                    createAttendanceTable(course);
                }
            }
        });
        submitComplaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object result = JOptionPane.showInputDialog(rootPanel, "Enter Complaint:");
                student.submitComplaint(new Complaint(String.valueOf(result), getSelectedAttendance()));
                JOptionPane.showMessageDialog(rootPanel, "Complaint Submitted");
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

    public Attendance getSelectedAttendance(){
        DefaultTableModel dm = (DefaultTableModel)attendanceTable.getModel();
        Vector<Vector> data = dm.getDataVector();
        for(int i = 0; i < data.size(); i++){
            if(i == attendanceTable.getSelectedRow()) {
                return (Attendance) data.get(i).get(4);
            }
        }
        return null;
    }
    public void append(String s) {
        try {
            Document doc = welcomeTextPane.getDocument();
            doc.insertString(doc.getLength(), s, null);
        } catch(BadLocationException exc) {
            exc.printStackTrace();
        }
    }
    public void clearAttendanceTable(){
        DefaultTableModel dm = (DefaultTableModel)attendanceTable.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
    public void createAttendanceTable(){
        LinkedList<Attendance> attendances = this.student.getAttendance();
        Object[][] data = new Object[attendances.size()][5];

        for(int j = 0; j < attendances.size(); j++){
            data[j][0] = attendances.get(j).getAttendanceID();
            data[j][1] = attendances.get(j).getDateOfAttendance();
            data[j][2] = attendances.get(j).getCourse();
            data[j][3] = attendances.get(j).isPresent();
            data[j][4] = attendances.get(j);

        }
        attendanceTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Date", "Course", "Present", "Attendance"}
        ));
        attendanceTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        attendanceTable.getColumnModel().getColumn(4).setMaxWidth(0);
        attendanceTable.getColumnModel().getColumn(4).setMinWidth(0);
    }
    public void createAttendanceTable(Course course){
        LinkedList<Attendance> attendances = this.student.getCourseRelatedAttendance(course);
        Object[][] data = new Object[attendances.size()][5];

        for(int j = 0; j < attendances.size(); j++){
            data[j][0] = attendances.get(j).getAttendanceID();
            data[j][1] = attendances.get(j).getDateOfAttendance();
            data[j][2] = attendances.get(j).getCourse();
            data[j][3] = attendances.get(j).isPresent();
            data[j][4] = attendances.get(j);

        }
        attendanceTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Date", "Course", "Present", "Attendance"}
        ));
        attendanceTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        attendanceTable.getColumnModel().getColumn(4).setMaxWidth(0);
        attendanceTable.getColumnModel().getColumn(4).setMinWidth(0);
    }
    public void createWarningTable(){
        LinkedList<Warning> warnings = this.student.getWarnings();
        Object[][] data = new Object[warnings.size()][4];

        for(int j = 0; j < warnings.size(); j++){
            data[j][0] = warnings.get(j).getWarningID();
            data[j][1] = warnings.get(j).getCourse();
            data[j][2] = warnings.get(j).getWarningNumber();
            data[j][3] = warnings.get(j);

        }
        warningsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Course", "Warning Number", "Warning"}
        ));
        warningsTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        warningsTable.getColumnModel().getColumn(3).setMaxWidth(0);
        warningsTable.getColumnModel().getColumn(3).setMinWidth(0);
    }
}
