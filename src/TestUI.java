import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TestUI extends JFrame {
    private final Teacher teacher;
    private Course course1;
    private Course course2;

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
    private JButton updateListButton;
    private JButton updateListButton1;

    TestUI(Teacher teacher){
        this.teacher = teacher;
        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Teacher UI");
        this.welcomeLabel.setText("Welcome Mr." + this.teacher.getName());

        this.comboBox1.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox1.addItem(course);
        }

        this.comboBox2.addItem("All Courses");
        for(Course course : this.teacher.getCourses()){
            this.comboBox2.addItem(course);
        }

        createTable1(teacher.getCourses().get(0),0);
        createTable2(teacher.getCourses().get(0));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(comboBox1.getSelectedItem() == "All Courses")){
                    course1 = (Course) comboBox1.getSelectedItem();
                    System.out.println(course1);
                    clearTable(table1);
                    createTable1(course1,0);
                }
                else if(comboBox1.getSelectedItem() == "All Courses"){
                    createTable1(teacher.getCourses().get(0),0);
                }
            }
        });
        updateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(table1);
                createTable1(course1,0);
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(comboBox1.getSelectedItem() == "All Courses")){
                    course2 = (Course) comboBox1.getSelectedItem();
                    System.out.println(course2);
                    clearTable(table2);
                    createTable2(course2);
                }
                else if(comboBox1.getSelectedItem() == "All Courses"){
                    createTable2(teacher.getCourses().get(0));
                }
            }
        });

        //Assuming your table model has a column for text at index 4
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //Get the selected row
                    int row = table1.getSelectedRow();
                    //Get the value of the text cell
                    String text = (String) table1.getValueAt(row, 4);
                    //Create a new JFrame with a JTextArea inside it
                    JFrame textFrame = new JFrame("Text Window");
                    JTextArea textArea = new JTextArea(text);
                    textArea.setEditable(false);
                    textFrame.add(textArea);
                    textFrame.setSize(300, 200);
                    textFrame.setVisible(true);
                }
            }
        });
        updateListButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable(table2);
                createTable2(course2);
            }
        });
    }


    public void createTable1(Course course, int recordNO){
        LinkedList<Attendance> attendances = this.teacher.getCourseRelatedAttendance(course,0);

        // Change the data type of the present column from Object to Boolean
        Object[][] data = new Object[attendances.size()][3];
        Boolean[] present = new Boolean[attendances.size()];

        for(int j = 0; j < attendances.size(); j++){
            data[j][0] = attendances.get(recordNO).getAttendanceID();
            data[j][1] = attendances.get(recordNO).getStudent().getName();
            data[j][2] = attendances.get(recordNO).getDateOfAttendance();
            // Use Boolean values for the present column
            present[j] = attendances.get(recordNO).isPresent();
        }

        // Create a table model with an overridden getColumnClass method
        DefaultTableModel tableModel = new DefaultTableModel(data, new String[] {"ID" ,"Student" , "Date"}) {
            @Override
            public Class<?> getColumnClass(int colIndex) {
                // Return Boolean.class for the present column
                if (colIndex == 3) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(colIndex);
                }
            }
        };

        // Add a column with Boolean values for the present column
        tableModel.addColumn("Present", present);

        // Set the table model to the table
        table1.setModel(tableModel);

        // Add a TableModelListener to handle the changes in the present attribute
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    boolean value = (boolean)tableModel.getValueAt(row, 3);
                    // Call the setPresent method with the new value
                    attendances.get(row).setPresent(value);
                }
            }
        });

        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
    }

    public void createTable2(Course course){
        LinkedList<Complaint> instanceOfComplaint = this.teacher.getCourseRelatedComplaints(course);

        // Change the data type of the present column from Object to Boolean
        Object[][] data = new Object[instanceOfComplaint.size()][5];
        Boolean[] accepted = new Boolean[instanceOfComplaint.size()];

        for(int j = 0; j < instanceOfComplaint.size(); j++){
            data[j][0] = instanceOfComplaint.get(j).getComplaintID();
            data[j][1] = instanceOfComplaint.get(j).getAttendance().getStudent();
            data[j][2] = instanceOfComplaint.get(j).getAttendance().getCourse();
            data[j][3] = instanceOfComplaint.get(j).getComplaintText();
            // Use Boolean values for the present column
            accepted[j] = instanceOfComplaint.get(j).isAccepted();
        }

        // Create a table model with an overridden getColumnClass method
        DefaultTableModel tableModel = new DefaultTableModel(data, new String[] {"ID" ,"Student" , "Course" , "Complaint Text" , "Accepted"}) {
            @Override
            public Class<?> getColumnClass(int colIndex) {
                // Return Boolean.class for the present column
                if (colIndex == 4) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(colIndex);
                }
            }
        };

        // Add a column with Boolean values for the present column
        tableModel.addColumn("Accepted", accepted);

        // Set the table model to the table
        table2.setModel(tableModel);

        // Add a TableModelListener to handle the changes in the present attribute
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    boolean value = (boolean)tableModel.getValueAt(row, 3);
                    // Call the setPresent method with the new value
                    instanceOfComplaint.get(row).setAccepted(value);
                }
            }
        });

        table2.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
    }

    public void clearTable(JTable table){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
}
