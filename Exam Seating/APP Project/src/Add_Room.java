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

public class Add_Room implements ActionListener {
    JFrame frame = new JFrame();
    JButton back_button = new JButton();
    JLabel room_label = new JLabel("Room Name:");
    JTextField room_textfield = new JTextField();
    JLabel student_label = new JLabel("Total Number of Students:");
    JTextField student_textfield = new JTextField();
    JButton save = new JButton();
    
    // Database connection parameters
    String dbUrl = "jdbc:mysql://localhost:3306/examseating"; // Change to your MySQL URL
    String dbUser = "username"; // Change to your MySQL username
    String dbPassword = "pwd"; // Change to your MySQL password

    Add_Room() {
        frame.setTitle("ADD ROOM Page");
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


        back_button.setIcon(new ImageIcon("C:\\Users\\91944\\Desktop\\FakeAPP\\APP Project\\lib\\images\\back.png"));
        back_button.setBounds(0, 0, 30, 30);
        back_button.setFocusable(false);
        back_button.setFont(new Font("ROBOTO", Font.BOLD, 20));
        back_button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        back_button.addActionListener(this);

        room_label.setBounds(50, 100, 200, 25);
        room_label.setFont(new Font("ROBOTO", Font.BOLD, 20));

        room_textfield.setBounds(350, 95, 200, 30);
        room_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        room_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        student_label.setBounds(50, 150, 250, 30);
        student_label.setFont(new Font("ROBOTO", Font.BOLD, 20));

        student_textfield.setBounds(350, 150, 200, 30);
        student_textfield.setFont(new Font("ROBOTO", Font.BOLD, 20));
        student_textfield.setBorder(BorderFactory.createLoweredBevelBorder());

        save.setText("SAVE");
        save.setBounds(440, 315, 100, 25);
        save.setFocusable(false);
        save.setFont(new Font("ROBOTO", Font.BOLD, 20));
        save.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        save.setFocusable(false);
        save.addActionListener(this);

        frame.add(back_button);
        frame.add(room_label);
        frame.add(room_textfield);
        frame.add(student_label);
        frame.add(student_textfield);
        frame.add(save);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button) {
            frame.dispose();
            new HomePage();
        } else if (e.getSource() == save) {
            String roomName = room_textfield.getText();
            String totalStudents = student_textfield.getText();

            if (saveDataToDatabase(roomName, totalStudents)) {
                // Data saved successfully, show the pop-up message
                showMessage("Data saved successfully.", "Success");
                room_textfield.setText(""); // Clear the text field
                student_textfield.setText(""); // Clear the text field
            } else {
                // Data couldn't be saved, show the error message
                showMessage("Error while saving data.", "Error");
            }
        }
    }

    // Method to save data to the database
    private boolean saveDataToDatabase(String roomName, String totalStudents) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Define the SQL query to insert data
            String insertQuery = "INSERT INTO rooms (room_name, seats) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set the values for the query
            preparedStatement.setString(1, roomName);
            preparedStatement.setString(2, totalStudents);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Close the resources
            preparedStatement.close();
            connection.close();

            return rowsAffected > 0; // If at least one row is affected, the data is saved
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Data couldn't be saved
        }
    }

    // Method to show a pop-up message
    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Add_Room();
        });
    }
}
