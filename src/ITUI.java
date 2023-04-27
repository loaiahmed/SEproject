import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class ITUI extends JFrame implements ActionListener{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JTable studentsTable;
    private JButton enrollStudentToCourseButton;
    private JComboBox studentsComboBox;
    private JButton logOutButton;
    private JTextPane welcomeMasterTextPane;
    private JButton addStudentButton;
    private JButton updateButton;
    private JButton removeStudentButton;
    private JButton removeStudentFromCourseButton;
    private JTable headTable;
    private JComboBox headComboBox;
    private JButton updateButton1;
    private JButton unAssignCourseFromButton;
    private JButton removeHeadButton;
    private JButton assignCourseToHeadButton;
    private JButton addHeadButton;
    private JTable teacherTable;
    private JComboBox teacherComboBox;
    private JButton updateButton2;
    private JButton assignCourseToTeacherButton;
    private JButton removeTeacherButton;
    private JButton addTeacherButton;
    private JButton unAssignCourseFromButton1;
    private JTable coursesTable;
    private JButton addCourseButton;
    private JButton updateButton3;
    private JButton removeCourseButton;

    private IT it;

    public ITUI(IT it) {

        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("IT UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.it = it;

        createStudentsTable(IT.allStudents);
        createHeadTable(IT.allHeads);
        createTeacherTable(IT.allTeachers);
        createCourseTable();


        studentsComboBox.addItem("All Students");
        studentsComboBox.addItem("All Students Without Courses");
        createComboBox(studentsComboBox);
        headComboBox.addItem("All Head");
        headComboBox.addItem("All Head Without Courses");
        createComboBox(headComboBox);
        teacherComboBox.addItem("All Teachers");
        teacherComboBox.addItem("All Teachers Without Courses");
        createComboBox(teacherComboBox);


        updateButton.addActionListener(this::actionPerformed );
        updateButton1.addActionListener(this::actionPerformed );
        updateButton2.addActionListener(this::actionPerformed );
        updateButton3.addActionListener(this::actionPerformed );

        removeStudentButton.addActionListener(this::actionPerformed);
        removeHeadButton.addActionListener(this::actionPerformed);
        removeTeacherButton.addActionListener(this::actionPerformed);
        removeCourseButton.addActionListener(this::actionPerformed);

        addStudentButton.addActionListener(this::actionPerformed);
        addHeadButton.addActionListener(this::actionPerformed);
        addTeacherButton.addActionListener(this::actionPerformed);
        addCourseButton.addActionListener(this::actionPerformed);

        enrollStudentToCourseButton.addActionListener(this::actionPerformed);
        assignCourseToHeadButton.addActionListener(this::actionPerformed);
        assignCourseToTeacherButton.addActionListener(this::actionPerformed);
        assignCourseToHeadButton.addActionListener(this::actionPerformed);

        removeStudentFromCourseButton.addActionListener(this::actionPerformed);
        unAssignCourseFromButton.addActionListener(this::actionPerformed);
        unAssignCourseFromButton1.addActionListener(this::actionPerformed);

        studentsComboBox.addActionListener(this::actionPerformed);
        headComboBox.addActionListener(this::actionPerformed);
        teacherComboBox.addActionListener(this::actionPerformed);

        logOutButton.addActionListener(new ActionListener() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton){
            clearTable(studentsTable);
            createStudentsTable(IT.allStudents);
        }if(e.getSource() == updateButton1){
            clearTable(headTable);
            createHeadTable(IT.allHeads);
        }if(e.getSource() == updateButton2){
            clearTable(teacherTable);
            createTeacherTable(IT.allTeachers);
        }if(e.getSource() == updateButton3){
            clearTable(coursesTable);
            createCourseTable();
        }

        if(e.getSource() == removeStudentButton){
            it.removeStudent((Student) getSelectedRow(studentsTable));
        }
        if(e.getSource() == removeHeadButton){
            it.removeHead((Head) getSelectedRow(headTable));
        }
        if(e.getSource() == removeTeacherButton){
            it.removeTeacher((Teacher) getSelectedRow(teacherTable));
        }
        if(e.getSource() == removeCourseButton){
            it.removeCourse((Course) getSelectedRow(coursesTable));
        }

        if(e.getSource() == addStudentButton || e.getSource() == addHeadButton || e.getSource() == addTeacherButton){
            SignUp signUp = new SignUp();
        }
        if(e.getSource() == addCourseButton){
            CourseCreator courseCreator = new CourseCreator();
        }


        if(e.getSource() == enrollStudentToCourseButton){
            if(studentsComboBox.getSelectedItem() == "All Courses" || studentsComboBox.getSelectedItem() == "All Students" || studentsComboBox.getSelectedItem() == "All Students Without Courses"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            it.enrollStudentToCourse((Student) getSelectedRow(studentsTable), (Course) studentsComboBox.getSelectedItem());
        }
        if(e.getSource() == assignCourseToHeadButton){
            if(headComboBox.getSelectedItem() == "All Courses" || headComboBox.getSelectedItem() == "All Head" || studentsComboBox.getSelectedItem() == "All Head Without Courses"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            it.assignHeadToCourse((Head) getSelectedRow(headTable), (Course) headComboBox.getSelectedItem());
        }
        if(e.getSource() == enrollStudentToCourseButton){
            if(teacherComboBox.getSelectedItem() == "All Courses" || teacherComboBox.getSelectedItem() == "All Teachers" || studentsComboBox.getSelectedItem() == "All Students"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            it.assignTeacherToCourse((Teacher) getSelectedRow(teacherTable), (Course) teacherComboBox.getSelectedItem());
        }


        if(e.getSource() == removeStudentFromCourseButton){
            if(studentsComboBox.getSelectedItem() == "All Courses" || studentsComboBox.getSelectedItem() == "All Students" || studentsComboBox.getSelectedItem() == "All Students Without Courses"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if(!it.removeStudentFromCourse((Student) getSelectedRow(studentsTable), (Course) teacherComboBox.getSelectedItem())){
                JOptionPane.showMessageDialog(rootPanel, "remove unsuccessful!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == unAssignCourseFromButton){
            if(headComboBox.getSelectedItem() == "All Courses" || headComboBox.getSelectedItem() == "All Head" || studentsComboBox.getSelectedItem() == "All Head Without Courses"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!it.unassignHeadToCourse((Head) getSelectedRow(headTable), (Course) headComboBox.getSelectedItem())){
                JOptionPane.showMessageDialog(rootPanel, "un assign unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == unAssignCourseFromButton1){
            if(teacherComboBox.getSelectedItem() == "All Courses" || teacherComboBox.getSelectedItem() == "All Teachers" || studentsComboBox.getSelectedItem() == "All Teachers Without Courses"){
                JOptionPane.showMessageDialog(rootPanel, "Course undefined? choose Course before banning!!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!it.unassignTeacherToCourse((Teacher) getSelectedRow(teacherTable), (Course) teacherComboBox.getSelectedItem())){
                JOptionPane.showMessageDialog(rootPanel, "un assign unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == studentsComboBox){
            if(studentsComboBox.getSelectedItem() == "All Courses"){
                clearTable(studentsTable);
                createStudentsTable(it.getAssignedStudents());
            } else if (studentsComboBox.getSelectedItem() == "All Students Without Courses") {
                clearTable(studentsTable);
                createStudentsTable(it.getUnAssignedStudents());
            } else if (studentsComboBox.getSelectedItem() == "All Students") {
                clearTable(studentsTable);
                createStudentsTable(IT.allStudents);
            } else {
                clearTable(studentsTable);
                Course course = (Course) studentsComboBox.getSelectedItem();
                System.out.println(course);
                createStudentsTable(it.getStudentsWithCourse(course));
            }
        }
        if(e.getSource() == headComboBox){
            if(headComboBox.getSelectedItem() == "All Courses"){
                clearTable(headTable);
                createHeadTable(it.getAssignedHeads());
            } else if (headComboBox.getSelectedItem() == "All head Without Courses") {
                clearTable(headTable);
                createHeadTable(it.getUnAssignedHeads());
            } else if (headComboBox.getSelectedItem() == "All Head") {
                clearTable(headTable);
                createStudentsTable(IT.allStudents);
            } else {
                clearTable(headTable);
                Course course = (Course) headComboBox.getSelectedItem();
                System.out.println(course);
                createHeadTable(it.getHeadWithCourse(course));
            }
        }
        if(e.getSource() == headComboBox){
            if(headComboBox.getSelectedItem() == "All Courses"){
                clearTable(teacherTable);
                createTeacherTable(it.getAssignedTeacher());
            } else if (studentsComboBox.getSelectedItem() == "All Teachers Without Courses") {
                clearTable(teacherTable);
                createTeacherTable(it.getAssignedTeacher());
            } else if (teacherComboBox.getSelectedItem() == "All Teachers") {
                clearTable(teacherTable);
                createTeacherTable(IT.allTeachers);
            } else {
                clearTable(teacherTable);
                Course course = (Course) teacherComboBox.getSelectedItem();
                System.out.println(course);
                createTeacherTable(it.getTeacherWithCourse(course));
            }
        }

    }

    public void createComboBox(JComboBox comboBox1){
        comboBox1.addItem("All Courses");
        Course[] userCourses = IT.allCourses.toArray(new Course[0]);
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
        studentsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Email", "GPA", "Number Of Absence", "Student"}
        ));
        studentsTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        studentsTable.getColumnModel().getColumn(5).setMaxWidth(0);
        studentsTable.getColumnModel().getColumn(5).setMinWidth(0);
    }

    public void createHeadTable(LinkedList<Head> heads){
        Object[][] data = new Object[heads.size()][6];

        for(int j = 0; j < heads.size(); j++){
            data[j][0] = heads.get(j).getUserID();
            data[j][1] = heads.get(j).getName();
            data[j][2] = heads.get(j).getAccount().getEmailAddress();
            data[j][3] = heads.get(j).getPhoneNumber();
            data[j][4] = heads.get(j);
        }
        headTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Email", "Phone Number", "Head"}
        ));
        headTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        headTable.getColumnModel().getColumn(4).setMaxWidth(0);
        headTable.getColumnModel().getColumn(4).setMinWidth(0);
    }
    public void createTeacherTable(LinkedList<Teacher> teachers){
        Object[][] data = new Object[teachers.size()][6];

        for(int j = 0; j < teachers.size(); j++){
            data[j][0] = teachers.get(j).getUserID();
            data[j][1] = teachers.get(j).getName();
            data[j][2] = teachers.get(j).getAccount().getEmailAddress();
            data[j][3] = teachers.get(j).getPhoneNumber();
            data[j][4] = teachers.get(j);
        }
        teacherTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Email", "Phone Number", "Teacher"}
        ));
        teacherTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        teacherTable.getColumnModel().getColumn(4).setMaxWidth(0);
        teacherTable.getColumnModel().getColumn(4).setMinWidth(0);
    }
    public void createCourseTable(){
        LinkedList<Course> allCourses = IT.allCourses;
        Object[][] data = new Object[allCourses.size()][6];

        for(int j = 0; j < allCourses.size(); j++){
            data[j][0] = allCourses.get(j).getCourseID();
            data[j][1] = allCourses.get(j).getCourseName();
            data[j][2] = allCourses.get(j).getCourseDoctor();
            data[j][3] = allCourses.get(j).getCourseCode();
            data[j][4] = allCourses.get(j);
        }
        coursesTable.setModel(new DefaultTableModel(
                data,
                new String[] {"ID", "Name", "Head Doctor", "Module Code", "Course"}
        ));
        coursesTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        coursesTable.getColumnModel().getColumn(4).setMaxWidth(0);
        coursesTable.getColumnModel().getColumn(4).setMinWidth(0);
    }
}
