import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TestUI extends JFrame implements ActionListener {
    Teacher teacher;
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logoutButton;
    private JPanel welcomePanel;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel welcomeLabel;
    private JTable table2;

    TestUI(Teacher teacher){
        this.teacher = teacher;
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Teacher UI");
        logoutButton.addActionListener(this);

        comboBox1.addActionListener(this);
        this.comboBox1.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox1.addItem(course.toString());
        }

        comboBox2.addActionListener(this);
        this.comboBox2.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox1.addItem(course.toString());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton);

        if(e.getSource() == comboBox1){
            if(!(comboBox1.getSelectedItem() == "All Courses")){
                Course course = (Course) comboBox1.getSelectedItem();
                System.out.println(course);
                createTable1(course);
            }
        }

        if(e.getSource() == comboBox2);
    }

    public void createTable1(Course c){
        LinkedList<Attendance> attendances = this.teacher.getCourseRelatedAttendance(c,0);

        Object[][] data = new Object[attendances.size()][3];

        for(int j = 0; j < attendances.size(); j++){
            data[j][0] = attendances.get(j).getAttendanceID();
            data[j][1] = attendances.get(j).getDateOfAttendance();
            data[j][2] = attendances.get(j).isPresent();
        }
        table1.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Date" , "Present"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
}
