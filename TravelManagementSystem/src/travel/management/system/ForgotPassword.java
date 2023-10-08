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

public class ForgotPassword extends JFrame implements ActionListener{
    JTextField tfusername , tfname ,tfpassword ,tfquestion,tfanswer;
    JButton search,retrieve,back,otp;
    JPanel panel;
    String Username;
	 String from = "choubeychanda2083@gmail.com";
     String host = "smtp.gmail.com"; 
     String Username1 = "choubeychandan2083@gmail.com";
     String password = "jgbfnzsdlxrtlsob";
    public ForgotPassword() {
        setBounds(300, 200, 859, 380);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("images/forgotpassword.jpg"));
        Image image1 = image.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image1);
        JLabel  img = new JLabel(icon);
        img.setBounds(580,70,200,200);
        add(img);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#ADD8E6"));
        panel.setBounds(30, 30, 500, 280);
        add(panel);
        
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40,20,100,25);
        lblusername.setFont(new Font("Tahoma" , Font.BOLD,14));
        panel.add(lblusername);
        
        
        tfusername = new JTextField();
        tfusername.setBounds(220,20,150,25);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        panel.add(tfusername); 
      
        search = new JButton("Search");
        search.setBackground(new Color(42,174,230));
        search.addActionListener(this);
        search.setBounds(380,20,100,25);
        search.setForeground(Color.WHITE);
        search.setBorder(new LineBorder(new Color(42,174,230)));
        panel.add(search);
        
         JLabel lblname = new JLabel("Name");
        lblname.setBounds(40,60,100,25);
        lblname.setFont(new Font("Tahoma" , Font.BOLD,14));
        panel.add(lblname);
        
        
        tfname = new JTextField();
        tfname.setBounds(220,60,150,25);
        tfname.setEditable(false);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        panel.add(tfname); 
        
         JLabel lblquestion = new JLabel("Security Question");
        lblquestion.setBounds(40,100,150,25);
        lblquestion.setFont(new Font("Tahoma" , Font.BOLD,14));
        panel.add(lblquestion);
        
        
        tfquestion = new JTextField();
        tfquestion.setBounds(220,100,150,25);
        tfquestion.setEditable(false);
        tfquestion.setBorder(BorderFactory.createEmptyBorder());
        panel.add(tfquestion); 
        
          JLabel lblanswer = new JLabel("Security Answer");
        lblanswer.setBounds(40,140,150,25);
        lblanswer.setFont(new Font("Tahoma" , Font.BOLD,14));
        panel.add(lblanswer);
        
        
        tfanswer = new JTextField();
        tfanswer.setBounds(220,140,150,25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        panel.add(tfanswer); 
        
         retrieve = new JButton("Retrieve");
        retrieve.setBackground(new Color(42,174,230));
        retrieve.addActionListener(this);
        retrieve.setBounds(380,140,100,25);
        retrieve.setForeground(Color.WHITE);
        retrieve.setBorder(new LineBorder(new Color(42,174,230)));
        panel.add(retrieve);
        
          JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40,180,150,25);
        lblpassword.setFont(new Font("Tahoma" , Font.BOLD,14));
        panel.add(lblpassword);
        
        
        tfpassword = new JTextField();
        tfpassword.setBounds(220,180,150,25);
        tfpassword.setEditable(false);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        panel.add(tfpassword);
        
         back = new JButton("Back");
        back.setBackground(new Color(42,174,230));
        back.setBounds(70,230,100,25);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBorder(new LineBorder(new Color(42,174,230)));
        panel.add(back);
        
       
        
        setVisible(true);  
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
          String query = "select * from account where username='" +tfusername.getText()+"'";
            System.out.println(query);
            Username = tfusername.getText();
            Conn c = new Conn();
            try {
                ResultSet result =c.s.executeQuery(query);
                while(result.next()){
                   tfname.setText(result.getString("name"));
                   tfquestion.setText(result.getString("security"));
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
            otp = new JButton("Get OTP");
            otp .setBackground(new Color(42,174,230));
            otp .setBounds(200,230,100,25);
            otp .setForeground(Color.WHITE);
            otp .addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	 Conn c = new Conn();
                     ResultSet rs;
                     String to = null;
     				try {
     					rs = c.s.executeQuery("select * from customer where username = '"+Username+"'");
     					while(rs.next()) {
     						to  = rs.getString("email");
     					}
     				} catch (SQLException e1) {
     					e1.printStackTrace();
     				}
                	 Mailer otp = new Mailer(to, from, host, Username1, password);
                    new CheckOtp(Username);
                }
            });
            otp .setBorder(new LineBorder(new Color(42,174,230)));
            panel.add(otp );
            panel.revalidate();
            panel.repaint();
        }
        else if(e.getSource() == retrieve){
          String query = "select password from account where username='"+tfusername.getText()+"' and answer='"+tfanswer.getText()+"'";
          Conn c = new Conn();
            try {
                ResultSet result = c.s.executeQuery(query);
                while(result.next()){
                tfpassword.setText(result.getString("password"));}
            } catch (SQLException ex) {
                Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    } 
    
    public static void main(String[] args) {
        new ForgotPassword();
    }

  

}
