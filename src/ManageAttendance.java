import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class ManageAttendance extends JFrame{
    private JTable table1;
    private JButton saveButton;
    private JPanel rootPanel;
    private LinkedList<Student> students;

    private DefaultTableModel model;
    ManageAttendance(LinkedList<Student> students){
        this.students = students;
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Manage Attendance");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        createStudentsTable(students);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAttendance();
                testingData();
                JOptionPane.showMessageDialog(rootPanel, "saved");
            }
        });
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void testingData(){
        for(Student student : students) {
            if(IT.allStudents.contains(student)){
                System.out.println(IT.allStudents.get( IT.allStudents.indexOf(student)).getAttendance().size() + " " + IT.allStudents.get( IT.allStudents.indexOf(student)).getStrike());
            }
        }
    }

    public void saveAttendance(){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();

        Vector<Vector> data = dm.getDataVector();
        for(int i = 0; i < data.size(); i++){
            students.get(i).addToAttendance(new Attendance(students.get(i), (boolean)data.get(i).get(3)));
        }
    }

    public void createStudentsTable(LinkedList<Student> students){
        Object[][] data = new Object[students.size()][6];

        boolean present = false;

        for(int j = 0; j < students.size(); j++){
            data[j][0] = students.get(j).getUserID();
            data[j][1] = students.get(j).getName();
            data[j][2] = students.get(j).getAccount().getEmailAddress();
            data[j][3] = present;
            data[j][4] = students.get(j).getStrike();
            data[j][5] = students.get(j);
        }
        model = new DefaultTableModel(data , new Object[] {"ID", "Name", "Email", "Attendance", "Number Of Absence", "Student"}) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        table1.setModel(model);

//        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
}
