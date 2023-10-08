package travel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Login extends JFrame implements ActionListener {

    JTextField tusername, tpassword;
    JButton login, signup, fpassword;

    Login() {
        setSize(800, 400);
        setLocation(200, 90);
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        add(panel);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/login.jpg"));
        Image image = icon.getImage().getScaledInstance(820, 350, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel jl = new JLabel(icon1);
        jl.setBounds(100, 0, 200, 200);
        panel.add(jl);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(400, 0, 400, 400);
        panel1.setBackground(Color.decode("#ADD8E6"));
        add(panel1);

        JLabel username = new JLabel("Username");
        username.setBounds(60, 30, 100, 30);
        username.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        panel1.add(username);

        tusername = new JTextField();
        tusername.setBounds(60, 65, 300, 30);
        username.setBorder(BorderFactory.createEmptyBorder());
        panel1.add(tusername);

        JLabel password = new JLabel("Password");
        password.setBounds(60, 100, 100, 30);
        password.setFont(new Font("SAN SERIF", Font.PLAIN, 20));
        panel1.add(password);

        tpassword = new JTextField();
        tpassword.setBounds(60, 135, 300, 30);
        password.setBorder(BorderFactory.createEmptyBorder());
        panel1.add(tpassword);

        signup = new JButton("SignUp");
        signup.setBounds(60, 200, 100, 30);
        signup.setBackground(new Color(42, 174, 230));
        signup.addActionListener(this);
        signup.setForeground(Color.WHITE);
        signup.setBorder(new LineBorder(new Color(42, 174, 230)));
        panel1.add(signup);

        login = new JButton("Login");
        login.setBounds(200, 200, 100, 30);
        login.addActionListener(this);
        login.setBackground(new Color(42, 174, 230));
        login.setForeground(Color.WHITE);
        login.setBorder(new LineBorder(new Color(42, 174, 230)));
        panel1.add(login);

        fpassword = new JButton("Forgot Password");
        fpassword.setBounds(100, 250, 150, 30);
        fpassword.addActionListener(this);
        fpassword.setBackground(new Color(42, 174, 230));
        fpassword.setForeground(Color.WHITE);
        fpassword.setBorder(new LineBorder(new Color(42, 174, 230)));
        panel1.add(fpassword);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            setVisible(false);
            new Signup();
        } else if (e.getSource() == fpassword) {
            setVisible(false);
            new ForgotPassword();
        } else if (e.getSource() == login) {
        	String query = "select * from account where username='" + tusername.getText() + "' AND password='" + tpassword.getText() + "'";
            Conn c = new Conn();
            try {
                ResultSet result = c.s.executeQuery(query);
                if (result.next()) {
                    setVisible(false);
                    new Dashboard(result.getString("username"));
                } else {
                	tusername.setText("Wrong Credential");
                	tusername.setForeground(Color.red);
                	tpassword.setText("Re-Enter");
                	tpassword.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, "Wrong Credentials");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            tusername.setForeground(Color.black);
            tusername.setText("");
            tpassword.setText("");
            tpassword.setForeground(Color.black);
        }
    }

    public static void main(String args[]) {
        new Login();
    }

}
