import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseCreator extends JFrame{
    private JTextField courseCodeField;
    private JTextField courseNameField;
    private JButton createButton;
    private JPanel rootPanel;

    CourseCreator(){
        this.setContentPane(rootPanel);
        this.pack();
        this.setTitle("Course Creation window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IT.allCourses.add(new Course(courseCodeField.getText(), courseNameField.getText()));
            }
        });
    }
}
