package travel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class ViewPackage extends JFrame implements ActionListener{
    JButton back;
    ViewPackage(String username){
        setBounds(350, 100,900,480);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel text = new JLabel("VIEW PACKAGE DETAIL");
        text.setFont(new Font("Tahoma" , Font.BOLD,20));
        text.setBounds(60,0,300,30);
        add(text);
        
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);
        
        JLabel labelusername = new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);
        
          JLabel lblid = new JLabel("Package");
        lblid.setBounds(30,90,150,25);
        add(lblid);
        
        JLabel labelpackage = new JLabel();
        labelpackage.setBounds(220,90,150,25);
        add(labelpackage);
        
        JLabel lbltime = new JLabel("On");
        lbltime.setBounds(30,130,150,25);
        add(lbltime);
        
        JLabel labeltime = new JLabel();
        labeltime.setBounds(220,130,150,25);
        add(labeltime);
        
          JLabel lblnumber = new JLabel("Total Person");
        lblnumber.setBounds(30,170,150,25);
        add(lblnumber);
        
        JLabel labelperson = new JLabel();
        labelperson.setBounds(220,170,150,25);
        add(labelperson);
        
        JLabel lblname = new JLabel("Id");
        lblname.setBounds(30,210,150,25);
        add(lblname);
        
        JLabel labelid = new JLabel();
        labelid.setBounds(220,210,150,25);
        add(labelid);
        
        JLabel lblgender = new JLabel("Number");
        lblgender.setBounds(30,250,150,25);
        add(lblgender);
        
        JLabel labelnumber = new JLabel();
        labelnumber.setBounds(220,250,150,25);
        add(labelnumber);
        
         JLabel lblcountry = new JLabel("Phone");
        lblcountry.setBounds(30,290,150,25);
        add(lblcountry);
        
        JLabel labelphone = new JLabel();
        labelphone.setBounds(220,290,150,25);
        add(labelphone);
        
        
        JLabel lbladdress = new JLabel("Price");
        lbladdress.setBounds(30,330,150,25);
        add(lbladdress);
        
        JLabel labelprice = new JLabel();
        labelprice.setBounds(220,330,150,25);
        add(labelprice);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(100,390,100,25);
        add(back);
      
       
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/bookedDetails.jpg"));
        Image i4 = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JLabel image1 = new JLabel(i5);
        image1.setBounds(450,20,500,400);
        add(image1);
                
        
        try {
            Conn c = new Conn();
            String query = "select * from bookpackage where username = '"+username+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
              labelusername.setText(rs.getString("username"));
              labelid.setText(rs.getString("id"));
              labelnumber.setText(rs.getString("number"));
              labelphone.setText(rs.getString("phone"));
              labelprice.setText(rs.getString("price"));
              labelperson.setText(rs.getString("persons"));
              labelpackage.setText(rs.getString("packages"));
              Timestamp timestamp = rs.getTimestamp("btime");
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
              String formattedTime = dateFormat.format(timestamp);
              labeltime.setText(formattedTime);
            }
        } catch (Exception e) {
        }

        setVisible(true);
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
          setVisible(false);
    }
    public static void main(String[] args) {
        new ViewPackage("SS");
    }

  
    
}
