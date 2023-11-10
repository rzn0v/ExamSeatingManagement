import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Assign_Students implements ActionListener {
    JFrame frame = new JFrame();
    JButton back_button = new JButton();
    JLabel stu_id = new JLabel("Student ID:");
    JTextField stuId_textfield = new JTextField();
    JLabel stu_name = new JLabel("Student Name:");
    JTextField stuName_textfield = new JTextField();
    JLabel stu_dept = new JLabel("Student Department:");
    JTextField stuDept_textfield = new JTextField();
    JLabel room_name = new JLabel("Room name:");
    JTextField roomName_textfield = new JTextField();
    JLabel stu_seatNo = new JLabel("Seat No:");
    JTextField stuSeatNo_textfield = new JTextField();
    JButton save = new JButton();

    // Database connection parameters
    String dbUrl = "jdbc:mysql://localhost:3306/examseating"; // Change to your MySQL URL
    String dbUser = "username"; // Change to your MySQL username
    String dbPassword = "pwd"; // Change to your MySQL password

    Assign_Students() {
        frame.setTitle("Assign Students Page");
        frame.getContentPane().setBackground(new Color(238, 238, 238));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 405);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 7));
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.decode("#F9F6EE"),7));
        frame.getContentPane().setBackground(Color.decode("#87CEEB"));


        back_button.setBounds(0, 0, 30, 30);
        back_button.setIcon(new ImageIcon("C:\\Users\\91944\\Desktop\\FakeAPP\\APP Project\\lib\\images\\back.png"));
        back_button.setFocusable(false);
        back_button.setFont(new Font("ROBOTO", Font.BOLD, 20));
        back_button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        back_button.addActionListener(this);

        stu_id.setBounds(50, 45, 200, 25);
        stu_id.setFont(new Font("ROBOTO", Font.BOLD, 20));

        stuId_textfield.setBounds(320, 40, 200, 30);
        stuId_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        stuId_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        stu_name.setBounds(50, 100, 200, 25);
        stu_name.setFont(new Font("ROBOTO", Font.BOLD, 20));

        stuName_textfield.setBounds(320, 95, 200, 30);
        stuName_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        stuName_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        stu_dept.setBounds(50, 155, 200, 25);
        stu_dept.setFont(new Font("ROBOTO", Font.BOLD, 20));

        stuDept_textfield.setBounds(320, 150, 200, 30);
        stuDept_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        stuDept_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        room_name.setBounds(50, 210, 200, 25);
        room_name.setFont(new Font("ROBOTO", Font.BOLD, 20));

        roomName_textfield.setBounds(320, 205, 200, 30);
        roomName_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        roomName_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        stu_seatNo.setBounds(50, 270, 200, 25);
        stu_seatNo.setFont(new Font("ROBOTO", Font.BOLD, 20));

        stuSeatNo_textfield.setBounds(320, 265, 200, 30);
        stuSeatNo_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        stuSeatNo_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        save.setText("SAVE");
        save.setBounds(440, 315, 100, 25);
        save.setFocusable(false);
        save.setFont(new Font("ROBOTO", Font.BOLD, 20));
        save.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        save.setFocusable(false);
        save.addActionListener(this);

        frame.add(back_button);
        frame.add(stu_id);
        frame.add(stuId_textfield);
        frame.add(stu_name);
        frame.add(stuName_textfield);
        frame.add(stu_dept);
        frame.add(stuDept_textfield);
        frame.add(room_name);
        frame.add(roomName_textfield);
        frame.add(stu_seatNo);
        frame.add(stuSeatNo_textfield);
        frame.add(save);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button) {
            frame.dispose();
            new HomePage();
        } else if (e.getSource() == save) {
            String studentID = stuId_textfield.getText();
            String studentName = stuName_textfield.getText();
            String studentDept = stuDept_textfield.getText();
            String roomName = roomName_textfield.getText();
            String seatNo = stuSeatNo_textfield.getText();

            if (saveDataToDatabase(studentID, studentName, studentDept, roomName, seatNo)) {
                showPopupMessage("Data Saved");
                clearTextFields();
            } else {
                System.out.println("Error while saving data.");
            }
        }
    }

    private void clearTextFields() {
        stuId_textfield.setText("");
        stuName_textfield.setText("");
        stuDept_textfield.setText("");
        roomName_textfield.setText("");
        stuSeatNo_textfield.setText("");
    }

    private void showPopupMessage(String message) {
        JFrame popupFrame = new JFrame("Message");
        JOptionPane.showMessageDialog(popupFrame, message);
    }

    private boolean saveDataToDatabase(String studentID, String studentName, String studentDept, String roomName, String seatNo) {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String insertQuery = "INSERT INTO students (student_id, student_name, student_batch, room_name, seat_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, studentName);
            preparedStatement.setString(3, studentDept);
            preparedStatement.setString(4, roomName);
            preparedStatement.setString(5, seatNo);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Assign_Students();
        });
    }
}
