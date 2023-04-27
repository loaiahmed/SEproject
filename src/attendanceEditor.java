import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class attendanceEditor extends JFrame{
    private JCheckBox presentOrNotCheckBox;
    private JButton saveButton;
    private JPanel rootPanel;
    private JLabel dateTime;
    private JLabel studentInfo;

    attendanceEditor(Attendance attendance){

        this.setContentPane(rootPanel);
//        this.setSize(900, 600);
        this.pack();
        this.setTitle("Attendance editor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        dateTime.setText(attendance.getDateOfAttendance().toString());
        studentInfo.setText(attendance.getStudent().toString());


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attendance.setPresent(presentOrNotCheckBox.isSelected());
                JOptionPane.showMessageDialog(rootPanel, "Saved");
            }
        });
    }
}
