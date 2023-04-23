import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherUI extends JFrame implements ActionListener {
    private final Teacher teacher;
    JComboBox jcomboBox1;
    JButton jbutton1;
    private JPanel panel1;

    TeacherUI(Teacher teacher){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450,450);
        this.setLayout(new FlowLayout());
        this.teacher = teacher;

        jcomboBox1.addItem("All Courses");
        jcomboBox1 = new JComboBox<>();
        for(Course c:teacher.getCourses()){
            jcomboBox1.addItem(c.toString());
        }
        jcomboBox1.setEditable(true);
        this.add(jcomboBox1);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jcomboBox1){
            System.out.println(jcomboBox1.getSelectedItem());
        }
    }
}
