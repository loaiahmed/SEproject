import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherUI extends JFrame implements ActionListener {
    private final Teacher teacher;
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;

    public TeacherUI(Teacher teacher) {
        this.setContentPane(rootPanel);
        this.teacher = teacher;
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Teacher UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
