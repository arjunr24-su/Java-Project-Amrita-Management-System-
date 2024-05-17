package amrita.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JButton login,signup,cancel;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");   
        lblusername.setBounds(40 ,20,100,20);
        add(lblusername);

        JTextField tfusername = new JTextField();
        tfusername.setBounds(150 ,20,150,20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40,70,100,20);
        add(lblpassword);

        JPasswordField tfpassword = new JPasswordField();
        tfpassword.setBounds(150,70,150,20);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40,140,120,30);
        login.addActionListener(this);
        add(login);

        signup = new JButton("Signup");
        signup.setBounds(190,140,120,30);
        signup.addActionListener(this);
        add(signup);

        cancel = new JButton("Cancel");
        cancel.setBounds(40,180,120,30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 =i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,200,200);
        add(image);

        setLocation(500,250);    
        setSize(600,300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()== login){
          new Project();
            setVisible(false);
        }else if(ae.getSource()== cancel){
            setVisible(false);
        }else if(ae.getSource()== signup){
            new Upload();
             setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
