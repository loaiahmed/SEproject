import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TestUI extends JFrame {
    private final Teacher teacher;
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JPanel welcomePanel;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel welcomeLabel;
    private JTable table2;
    private JComboBox comboBox3;

    TestUI(Teacher teacher){
        this.teacher = teacher;
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Teacher UI");

        this.comboBox1.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox1.addItem(course.toString());
        }

        this.comboBox2.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox1.addItem(course.toString());
        }

        createTable1(teacher.getCourses().get(0),0);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(comboBox1.getSelectedItem() == "All Courses")){
                    Course course = (Course) comboBox1.getSelectedItem();
                    System.out.println(course);
                    clearTable();
                    createTable1(course,0);
                }
                else if(comboBox1.getSelectedItem() == "All Courses"){
                    createTable1(teacher.getCourses().get(0),0);
                }
            }
        });
    }

    public void createTable1(Course course, int recordNO){
        LinkedList<Attendance> attendances = this.teacher.getCourseRelatedAttendance(course,0);

        Object[][] data = new Object[attendances.size()][4];

        for(int j = 0; j < attendances.size(); j++){
            data[j][0] = attendances.get(recordNO).getAttendanceID();
            data[j][1] = attendances.get(recordNO).getStudent().getName();
            data[j][2] = attendances.get(recordNO).getDateOfAttendance();
            data[j][3] = attendances.get(recordNO).isPresent();
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"ID" ,"Student" , "Date" , "Present"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
    }

    public void clearTable(){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }

}
