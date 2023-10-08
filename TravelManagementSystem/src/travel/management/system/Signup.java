package travel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener {

    JButton btn, back;
    JTextField usertf, nametf, passtf,answer;
    Choice security;

    Signup() {
        setBounds(350, 200, 700, 360);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#ADD8E6"));
        panel.setBounds(0, 0, 400, 400);
        panel.setLayout(null);
        add(panel);

        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblusername.setBounds(50, 23, 123, 25);
        panel.add(lblusername);

        usertf = new JTextField();
        usertf.setBounds(190, 20, 180, 25);
        usertf.setBorder(BorderFactory.createEmptyBorder());
        panel.add(usertf);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblname.setBounds(50, 60, 123, 25);
        panel.add(lblname);

        nametf = new JTextField();
        nametf.setBounds(190, 60, 180, 25);
        nametf.setBorder(BorderFactory.createEmptyBorder());
        panel.add(nametf);

        JLabel lblpass = new JLabel("Password");
        lblpass.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpass.setBounds(50, 100, 123, 25);
        panel.add(lblpass);

        passtf = new JTextField();
        passtf.setBounds(190, 100, 180, 25);
        passtf.setBorder(BorderFactory.createEmptyBorder());
        panel.add(passtf);

        JLabel lblsecurity = new JLabel("Security Question");
        lblsecurity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblsecurity.setBounds(50, 140, 123, 25);
        panel.add(lblsecurity);

        security = new Choice();
        security.add("Fav Teacher");
        security.add("Pet name");
        security.add("House No");
        security.add("Fav game");

        security.setBounds(190, 140, 123, 25);
        panel.add(security);

        JLabel lblanswer = new JLabel("Answer");
        lblanswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblanswer.setBounds(50, 200, 123, 25);
        panel.add(lblanswer);

        answer = new JTextField();
        answer.setBounds(190, 200, 180, 25);
        answer.setBorder(BorderFactory.createEmptyBorder());
        panel.add(answer);

        btn = new JButton("Create");
        btn.setBackground(new Color(133, 193, 233));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        btn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn.setBounds(80, 250, 100, 30);
        panel.add(btn);

        back = new JButton("Back");
        back.setBackground(new Color(133, 193, 233));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setBounds(250, 250, 100, 30);
        panel.add(back);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/signup.png"));
        Image image = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(image);
        JLabel jl = new JLabel(icon1);
        jl.setBounds(400, 30, 250, 250);
        add(jl);

        setVisible(true);

    }
    
    public boolean valid() {
        if (usertf.getText().equals("")) {
            usertf.setText("Fill The Credential");
            return false;
        } else if (passtf.getText().equals("")) {
            passtf.setText("Fill The Credential");
            return false;
        } else if (nametf.getText().equals("")) {
            nametf.setText("Fill The Credential");
            return false;
        } else if (answer.getText().equals("")) {
            answer.setText("Fill The Credential");;
            return false;
        } 
        return true;
    }


    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            String username = usertf.getText();
            String password = passtf.getText();
            String name = nametf.getText();
            String question = security.getSelectedItem();
            String ans = answer.getText();
            
            if(valid()) {
           
            String query = "insert into account values('"+username+"','"+name+"','"+password+"','"+question+"','"+ans+"')";
            String sql = "Select * from account where username='"+username+"'";
            try {
				Conn c = new Conn();
			ResultSet rs = c.s.executeQuery(sql);
			if(rs.next()) {
				usertf.setText("Already Taken");
				usertf.setForeground(Color.RED);
			}
			else {
				 try {
		                Conn c1 = new Conn();
		                c1.s.executeUpdate(query);
		                JOptionPane.showMessageDialog(null, "Successfully Account Created");
		                setVisible(false);
		                new Login();
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
			}
			} catch (Exception e2) {
				// TODO: handle exception
			}
            }
           
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String args[]) {
        new Signup();
    }

}
