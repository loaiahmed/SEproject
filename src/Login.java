import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JButton login;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel loginPanel;
    private JRadioButton studentRadioButton;
    private JRadioButton headRadioButton;
    private JRadioButton teacherRadioButton;

    public Login() {

        this.setContentPane(loginPanel);
//        this.setSize(400, 400);
        this.pack();
        this.setTitle("Log In window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(login, "button been pressed");

                String password = String.valueOf(passwordField1.getPassword());
                if(studentRadioButton.isSelected()) {
                    if (IT.isStudentWithAccount(textField1.getText(), password)) {
                        JOptionPane.showMessageDialog(login, "account found");
                    } else {
                        JOptionPane.showMessageDialog(login, "account not found");
                    }
                }
                if(headRadioButton.isSelected()) {
                    if (IT.isHeadWithAccount(textField1.getText(), password)) {
                        JOptionPane.showMessageDialog(login, "account found");
                    } else {
                        JOptionPane.showMessageDialog(login, "account not found");
                    }
                }
                if(teacherRadioButton.isSelected()) {
                    if (IT.isTeacherWithAccount(textField1.getText(), password)) {
                        JOptionPane.showMessageDialog(login, "account found");
                    } else {
                        JOptionPane.showMessageDialog(login, "account not found");
                    }
                }
            }
        });
    }
}
