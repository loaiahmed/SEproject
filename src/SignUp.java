import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SignUp extends JFrame{
    private JTextField userNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField checkPasswordField;
    private JTextField phoneNumberField;
    private JTextField nameField;
    private JButton signUpButton;
    private JRadioButton teacherRadioButton;
    private JRadioButton studentRadioButton;
    private JRadioButton headRadioButton;
    private JPanel signUpPanel;

    public SignUp() {

//        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize((int)size.getWidth()/3, (int)size.getHeight()/4);
        this.setContentPane(signUpPanel);
        this.pack();
        this.setTitle("Sign Up window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(passwordField.getPassword());

                if(userNameField.getText().trim().isEmpty() || password.trim().isEmpty() || nameField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(signUpPanel, "a field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!checkEmail()){
                    JOptionPane.showMessageDialog(signUpPanel, "Email is not right", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!(Arrays.equals(passwordField.getPassword(), checkPasswordField.getPassword()))){
                    JOptionPane.showMessageDialog(signUpPanel, "Password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    JOptionPane.showConfirmDialog(signUpPanel, "Confirm SignUp?");
                }

                if(teacherRadioButton.isSelected()){
                    Teacher teacher = new Teacher(nameField.getText(), phoneNumberField.getText(), new Account(userNameField.getText(), emailField.getText(), password));
                    IT.allTeachers.add(teacher);

                    JOptionPane.showMessageDialog(signUpPanel, "Account Created");
                }
                else if(headRadioButton.isSelected()){
                    Head head = new Head(nameField.getText(), phoneNumberField.getText(), new Account(userNameField.getText(), emailField.getText(), password));
                    IT.allHeads.add(head);

                    JOptionPane.showMessageDialog(signUpPanel, "Account Created");
                }
                else if(studentRadioButton.isSelected()){
                    Student student = new Student(nameField.getText(), phoneNumberField.getText(), new Account(userNameField.getText(), emailField.getText(), password));
                    IT.allStudents.add(student);

                    JOptionPane.showMessageDialog(signUpPanel, "Account Created");
                }
                else {
                    JOptionPane.showMessageDialog(signUpPanel, "SignUp failed: Select a checkBox", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public boolean checkEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(emailField.getText()).matches();
    }
}

/*
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        (pat.matcher(email).matches())
 */
