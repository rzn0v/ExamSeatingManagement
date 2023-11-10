import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class Room_Data implements ActionListener {
    JFrame frame = new JFrame();
    JButton back_button = new JButton();
    JTable table = new JTable();
    DefaultTableModel tableModel = new DefaultTableModel();

    Room_Data() {
        frame.setTitle("ROOM DATA Page");
        frame.getContentPane().setBackground(new Color(238, 238, 238));
        frame.setIconImage(new ImageIcon("Image file location for logo").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 405);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 7));
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.decode("#F9F6EE"),7));
       




        back_button.setIcon(new ImageIcon("Image file location for logo"));
        back_button.setBounds(0, 0, 30, 30);
        back_button.setFocusable(false);
        back_button.setFont(new Font("ROBOTO", Font.BOLD, 20));
        back_button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        back_button.addActionListener(this);

        // Create the table and table model
        tableModel.addColumn("Room Name");
        tableModel.addColumn("Seat Number");
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Student Batch");

        // Populate the table with data
        displayDataInTable(tableModel);

        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 540, 335);
        frame.add(scrollPane);

        frame.add(back_button);

        frame.setVisible(true);
    }

    private void displayDataInTable(DefaultTableModel tableModel) {
        Connection connection = null;
        try {
            // Connect to your database
            String jdbcUrl = "jdbc:mysql://localhost:3306/examseating";
            String username = "username";
            String password = "pwd";
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Modify your SQL query to include an ORDER BY clause
            String query = "SELECT rooms.room_name, students.seat_number, students.student_id, students.student_name, students.student_batch " +
                           "FROM rooms INNER JOIN students ON rooms.room_name = students.room_name " +
                           "ORDER BY rooms.room_name"; // Add the ORDER BY clause here
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
          


            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getString("room_name"));
                rowData.add(resultSet.getInt("seat_number"));
                rowData.add(resultSet.getInt("student_id"));
                rowData.add(resultSet.getString("student_name"));
                rowData.add(resultSet.getString("student_batch"));
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button) {
            frame.dispose();
            new HomePage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Room_Data();
        });
    }
}