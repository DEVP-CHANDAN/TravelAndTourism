package travel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.awt.event.ActionListener;

public class ViewBookedHotel extends JFrame implements ActionListener{
    JButton back,delete;
    String username = null;
    ViewBookedHotel(String username){
        this.username = username;
        setBounds(350, 100,1000,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel text = new JLabel("VIEW BOOKED HOTEL DETAIL");
        text.setFont(new Font("Tahoma" , Font.BOLD,20));
        text.setBounds(60,0,300,30);
        add(text);
        

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);
        
        JLabel labelusername = new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);
        
          JLabel lblname = new JLabel("Hotel Name");
          lblname.setBounds(30,90,150,25);
        add(lblname);
        
        JLabel labelname = new JLabel();
        labelname.setBounds(220,90,150,25);
        add(labelname);
        
          JLabel lblperson = new JLabel("Total Person");
          lblperson.setBounds(30,130,150,25);
        add(lblperson);
        
        JLabel labelperson = new JLabel();
        labelperson.setBounds(220,130,150,25);
        add(labelperson);
        
        JLabel lbldays = new JLabel("Total Days");
        lbldays.setBounds(30,170,150,25);
        add(lbldays);
        
        JLabel labeldays = new JLabel();
        labeldays.setBounds(220,170,150,25);
        add(labeldays);
        
        JLabel lblac = new JLabel("AC/Non-AC");
        lblac.setBounds(30,210,150,25);
        add(lblac);
        
        JLabel labelac = new JLabel();
        labelac.setBounds(220,210,150,25);
        add(labelac);
        
        JLabel lblfood = new JLabel("Food Included?");
        lblfood.setBounds(30,250,150,25);
        add(lblfood);
        
        JLabel labelfood = new JLabel();
        labelfood.setBounds(220,250,150,25);
        add(labelfood);
        
        JLabel lblid = new JLabel("Id");
        lblid.setBounds(30,290,150,25);
        add(lblid);
        
        JLabel labelid = new JLabel();
        labelid.setBounds(220,290,150,25);
        add(labelid);
        
        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30,330,150,25);
        add(lblnumber);
        
        JLabel labelnumber = new JLabel();
        labelnumber.setBounds(220,330,150,25);
        add(labelnumber);
        
         JLabel lblphone = new JLabel("Phone");
         lblphone.setBounds(30,370,150,25);
        add(lblphone);
        
        JLabel labelphone = new JLabel();
        labelphone.setBounds(220,370,150,25);
        add(labelphone);
        
        
        JLabel lblprice = new JLabel("Total Cost");
        lblprice.setBounds(30,410,150,25);
        add(lblprice);
        
        JLabel labelprice = new JLabel();
        labelprice.setBounds(220,410,150,25);
        add(labelprice);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(50,450,100,25);
        add(back);
        
        delete = new JButton("Delete");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        delete.setBounds(200,450,100,25);
        add(delete);
      
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/bookedDetails.jpg"));
        Image i4 = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JLabel image1 = new JLabel(i5);
        image1.setBounds(450,20,500,400);
        add(image1);
                
        
        try {
            Conn c = new Conn();
            String query = "select * from bookhotel where username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.isBeforeFirst()) { 
            	 while (rs.next()) {
                     labelusername.setText(rs.getString("username"));
                     labelname.setText(rs.getString("name"));
                     labelperson.setText(rs.getString("persons"));
                     labeldays.setText(rs.getString("days"));
                     labelac.setText(rs.getString("ac"));
                     labelfood.setText(rs.getString("food"));
                     labelid.setText(rs.getString("id"));
                     labelnumber.setText(rs.getString("number"));
                     labelphone.setText(rs.getString("phone"));
                     labelprice.setText(rs.getString("price"));
                 }
                
            } else {
            	JOptionPane.showMessageDialog(this, "No hotel is booked for the user: " + username, "No Booking Found", JOptionPane.INFORMATION_MESSAGE);
            	this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
    	  if(e.getSource() == delete) {
    		  try {
    	            Conn c = new Conn();
    	            String deleteQuery = "DELETE FROM bookhotel WHERE username = '" + username + "'";
    	            int rowsDeleted = c.s.executeUpdate(deleteQuery);
    	            if (rowsDeleted > 0) {
    	                JOptionPane.showMessageDialog(ViewBookedHotel.this, "Booking deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	                setVisible(false); 
    	            }
    	        } catch (Exception ex) {
    	            ex.printStackTrace();
    	        }
    	  }
    	  else {
    		  setVisible(false);  
    	  }
    }
    public static void main(String[] args) {
        new ViewBookedHotel ("SS");
    }

  
    
}
