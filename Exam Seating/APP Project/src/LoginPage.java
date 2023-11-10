import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



class LoginPage implements ActionListener
{

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/examseating";
    private static final String JDBC_USER = "username";
    private static final String JDBC_PASSWORD = "pwd";

    //use this objects in other function
    JFrame frame;
    JButton login_button;
    JButton clearButton;
    JTextField admin_textfield = new JTextField();;
    JPasswordField password_passwordfield;

    JLabel message;

    // Variable stores the data
    String admin ;
    String password;
    
    // constructor
    LoginPage()
    {
        // Ojects
        frame = new JFrame();
        JLabel login_label  = new JLabel("LOGIN");
        JLabel admin_label = new JLabel("Admin");
        JLabel password_label = new JLabel("Password");
        
        password_passwordfield = new JPasswordField();
        login_button = new JButton();
        clearButton = new JButton();
        Image img = new ImageIcon("Image file location for logo").getImage();
        
        JPanel rightPanel = new JPanel();
        message = new JLabel();

        frame.setTitle("Login Page");
        frame.getContentPane().setBackground(Color.decode("#87CEEB"));
        frame.setIconImage(new ImageIcon("Image file location for logo").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,405);
        frame.setResizable(false);;
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.decode("#F9F6EE"),7));
       


        // panel components
        rightPanel.setBackground(Color.decode("#0096FF"));
        rightPanel.setBounds(400,0,386,450);
        
        
        // login lable components
        login_label.setBounds(200, 55,100,30);
        login_label.setForeground(Color.decode("#F9F6EE"));
        login_label.setFont(new Font("ROBOTO",Font.BOLD,30));


        // admin lable components
        admin_label.setBounds(58, 130,60,25);
        admin_label.setFont(new Font("ROBOTO",Font.BOLD,20));


        // admin textfield components
        admin_textfield.setBounds(150, 125,200,30);
        admin_textfield.setFont(new Font("ROBOTO",Font.BOLD,20));
        admin_textfield.setBorder(BorderFactory.createLoweredBevelBorder());


        // password lable components
        password_label.setBounds(25, 200,100,25);
        password_label.setFont(new Font("ROBOTO",Font.BOLD,20));


        // password passwordfield components
        password_passwordfield.setBounds(150, 195,200,30);
        password_passwordfield.setFont(new Font("ROBOTO",Font.BOLD,30));
        password_passwordfield.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        

        // login button
        login_button.setText("Login");
        login_button.setBounds(255, 260,95,30);
        login_button.setFocusable(false);
        login_button.setFont(new Font("ROBOTO",Font.BOLD,20));
        login_button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        login_button.addActionListener(this);


        // clear button
        clearButton.setText("Clear");
        clearButton.setBounds(150, 260,95,30);
        clearButton.setFocusable(false);
        clearButton.setFont(new Font("ROBOTO",Font.BOLD,20));
        clearButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        clearButton.addActionListener(this);


        // message to show user
        message.setFont(new Font("ROBOTO",Font.CENTER_BASELINE,25));
        message.setBounds(120, 330 , 200, 35);
        message.setForeground(Color.BLUE);


        // Logo label in Right palek
        JLabel MainLogo = new JLabel();
        rightPanel.setLayout(null); // to set position using bounds
        MainLogo.setBounds(90, 70, 200, 200);
        Image resizeimage = img.getScaledInstance(MainLogo.getWidth(), MainLogo.getHeight(),Image.SCALE_SMOOTH);
        MainLogo.setIcon(new ImageIcon(resizeimage));


        
        // add Mainlogo lobel in right panel
        rightPanel.add(MainLogo);


        


        // difining frame
        
        



        // add other components to the frame
        frame.add(rightPanel);
        frame.add(login_label);
        frame.add(admin_label);
        frame.add(admin_textfield);
        frame.add(password_label);
        frame.add(password_passwordfield);
        
        frame.add(login_button);
        frame.add(clearButton);
        frame.add(message);



        frame.setVisible(true);
    }


    // Implemented function in ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            admin_textfield.setText("");
            password_passwordfield.setText("");
            
        }

        if (e.getSource() == login_button) {
            admin = admin_textfield.getText();
            password = String.valueOf(password_passwordfield.getPassword());

            if (authenticateUser(admin, password) )
            {
                
                // lable for icon
                JLabel sucessicon = new JLabel();
                // icon location
                sucessicon.setIcon(new ImageIcon("Image file location for logo"));

                // labele for message
                JLabel sucessmessage = new JLabel("Admin Login Sucessful");
                sucessmessage.setFont(new Font("ROBOTO",Font.BOLD,15));

                // panel to place icon and message
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(sucessicon);
                panel.add(sucessmessage);
                
                // open the message box
                JOptionPane.showMessageDialog(null,panel,"ADMIN LOGIN",JOptionPane.PLAIN_MESSAGE);

                frame.dispose(); // Close the login page
                //new AdminPage(); // Open the admin page
                new HomePage();

            } 
            else 
            {
                // lable for icon
                JLabel wrongicon = new JLabel();
                // icon location
                wrongicon.setIcon(new ImageIcon("Image file location for logo"));

                // labele for message
                JLabel wrongmessage = new JLabel("Login Failed. Invalid admin, password.");
                wrongmessage.setFont(new Font("ROBOTO",Font.BOLD,15));

                // panel to place icon and message
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(wrongicon);
                panel.add(wrongmessage);
                
                // open the message box
                JOptionPane.showMessageDialog(null,panel,"LOGIN FAILED",JOptionPane.DEFAULT_OPTION);
                admin_textfield.setText("");
                password_passwordfield.setText("");
            }
        }
    }

    private boolean authenticateUser(String admin, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT * FROM login WHERE admin = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
