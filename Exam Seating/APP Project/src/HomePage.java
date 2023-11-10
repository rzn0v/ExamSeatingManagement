import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class HomePage implements ActionListener
{
    JFrame frame = new JFrame();
    JButton add_Button = new JButton();
    JButton room_no_Button = new JButton();
    JButton enter_room_data = new JButton();
    JButton logout = new JButton();

    //JButton room_no_Button = new JButton();

    HomePage()
    {
        frame.setTitle("Home Page");
        frame.getContentPane().setBackground(new Color(238, 238, 238));
        frame.setIconImage(new ImageIcon("C:\\Users\\91944\\Desktop\\FakeAPP\\APP Project\\lib\\images\\m.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,405);
        frame.setResizable(false);;
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,7));
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.decode("#F9F6EE"),7));
        frame.getContentPane().setBackground(Color.decode("#87CEEB"));

        add_Button.setText("Add Room");
        add_Button.setBounds(50, 25,200,100);
        add_Button.setFocusable(false);
        add_Button.setFont(new Font("ROBOTO",Font.BOLD,20));
        add_Button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add_Button.setFocusable(false);
        //add_Button.setBackground();
        add_Button.addActionListener(this);

        room_no_Button.setText("Room Data");
        room_no_Button.setBounds(325, 25,200,100);
        room_no_Button.setFocusable(false);
        room_no_Button.setFont(new Font("ROBOTO",Font.BOLD,20));
        room_no_Button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        room_no_Button.setFocusable(false);
        //room_no_Button.setBackground();
        room_no_Button.addActionListener(this);

        enter_room_data.setText("Assign Students");
        enter_room_data.setBounds(50, 175,200,100);
        enter_room_data.setFocusable(false);
        enter_room_data.setFont(new Font("ROBOTO",Font.BOLD,20));
        enter_room_data.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        enter_room_data.setFocusable(false);
        //room_no_Button.setBackground();
        enter_room_data.addActionListener(this);

        logout.setText("LOGOUT");
        logout.setBounds(440, 315,100,25);
        logout.setFocusable(false);
        logout.setFont(new Font("ROBOTO",Font.BOLD,20));
        logout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        logout.setFocusable(false);
        //room_no_Button.setBackground();
        logout.addActionListener(this);






        frame.add(add_Button);
        frame.add(room_no_Button);
        frame.add(enter_room_data);
        frame.add(logout);




        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==add_Button)
        {
            frame.dispose();
            new Add_Room();
        }
        if(e.getSource()==room_no_Button)
        {
            frame.dispose();
            new Room_Data();
        }
        if(e.getSource()==enter_room_data)
        {
            frame.dispose();
            new Assign_Students();
        }
        if(e.getSource()==logout)
        {
            frame.dispose();
            new LoginPage();
        }

    }
    

    



    public static void main(String[] args) {
        new HomePage();
    }
}
