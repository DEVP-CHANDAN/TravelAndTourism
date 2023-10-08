package travel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class BookPackage extends JFrame implements ActionListener {
 
    private JDateChooser checkOutDateChooser;
    Choice cpackage;
    JTextField tfpersons;
    String username;
    JLabel labelusername, labelid, labelnumber, labelphone, labelprice;
    JButton checkprice, bookpackage, back;

    BookPackage(String username) {
        this.username = username;
        setBounds(250, 100, 1100, 600);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("BOOK PACKAGE");
        text.setBounds(100, 10, 200, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(text);

        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblusername.setBounds(40, 70, 100, 20);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelusername.setBounds(250, 70, 100, 20);
        add(labelusername);

        JLabel lblpackage = new JLabel("Select Packages");
        lblpackage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpackage.setBounds(40, 110, 150, 20);
        add(lblpackage);

        cpackage = new Choice(); 
        
        try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("Select * from package");
			while(rs.next()) {
				 cpackage.add(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        cpackage.setBounds(250, 110, 200, 20);
        add(cpackage);

        JLabel lblperson = new JLabel("Total Person");
        lblperson.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblperson.setBounds(40, 150, 150, 25);
        add(lblperson);

        tfpersons = new JTextField("1");
        tfpersons.setBounds(250, 150, 200, 25);
        add(tfpersons);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(40, 190, 150, 25);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(250, 190, 150, 25);
        add(labelid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblnumber.setBounds(40, 230, 150, 25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(250, 230, 150, 25);
        add(labelnumber);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblphone.setBounds(40, 270, 150, 20);
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(250, 270, 150, 20);
        add(labelphone);
        
        JLabel checkOutLabel = new JLabel("Booking Date");
        checkOutDateChooser = new JDateChooser();
        checkOutLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        checkOutLabel.setBounds(40, 310, 150, 20);
        checkOutDateChooser.setBounds(250, 310, 150, 20);
        add(checkOutLabel);
        add(checkOutDateChooser);

        JLabel lblprice = new JLabel("Total Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblprice.setBounds(40, 350, 150, 20);
        add(lblprice);

        labelprice = new JLabel();
        labelprice.setBounds(250, 350, 200, 25);
        add(labelprice);

        try {
            Conn c = new Conn();
            String query = "select * from customer where username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelphone.setText(rs.getString("phone"));

            }
        } catch (Exception e) {
        }

        checkprice = new JButton("Check Price");
        checkprice.setBackground(Color.BLACK);
        checkprice.addActionListener(this);
        checkprice.setForeground(Color.WHITE);
        checkprice.setBounds(60, 400, 120, 25);
        add(checkprice);

        bookpackage = new JButton("Book Package");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.addActionListener(this);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200, 400, 120, 25);
        add(bookpackage);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBounds(340, 400, 120, 25);
        add(back);
      
        
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/bookpackage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(i2);
        JLabel image = new JLabel(icon);
        image.setBounds(550,70,500,300);
        add(image);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BookPackage("Chandan");
    }
    
    public boolean validBook(String username){
    	Conn c = new Conn();
    	String sql = "Select * from bookpackage where username = '"+username+"'";
        try {
			ResultSet rs = c.s.executeQuery(sql);
			while (rs.next()) {
			    Date bookingDate = rs.getDate("btime");
			    LocalDate bookingLocalDate = bookingDate.toLocalDate();
			    java.util.Date selectedDate = checkOutDateChooser.getDate();			    
			    LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    LocalDate dateInFuture = selectedLocalDate.plusDays(10);
			    if (bookingLocalDate.isAfter(dateInFuture)) {
			      return true;
			    }  
		    }
        }
		 catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String cost1 = null;

        if(e.getSource() == checkprice){
          String pack = cpackage.getSelectedItem();
          int cost = 0;
          
          try {
			Conn c = new Conn();
			String sql = "select * from package where name='" + pack+"'";
			ResultSet rs = c.s.executeQuery(sql);
            if(rs.next()) {
	        cost += Integer.parseInt(rs.getString("price"));}
            cost1 = cost+"";
		} catch (Exception e2) {
			e2.printStackTrace();
		}
          int persons =  Integer.parseInt(tfpersons.getText()) ;
          cost *= persons;
          labelprice.setText( "Rs " + cost);
          
        }
        else if (e.getSource() == bookpackage) {
        	if (validBook(username)) {
        	    demo.PaymentCallback paymentCallback = new demo.PaymentCallback() {
        	        @Override
        	        public void onPaymentSuccessful(String totalAmount) {
        	            try {
        	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	                Conn c = new Conn();

        	                c.s.executeUpdate("INSERT INTO bookpackage (username, packages, persons, id, number, phone, price, time, btime) VALUES ('" + labelusername.getText() + "','" + cpackage.getSelectedItem() + "','" + tfpersons.getText() + "','" + labelid.getText() + "','" + labelnumber.getText() + "','" + labelphone.getText() + "','" + totalAmount + "','" + new Timestamp(System.currentTimeMillis()) + "','" + dateFormat.format(checkOutDateChooser.getDate()) + "')");
        	                JOptionPane.showMessageDialog(null, "Package booked Successfully");
        	                setVisible(false);
        	            } catch (Exception e1) {
        	                e1.printStackTrace();
        	            }
        	        }
        	    };
        	    new demo(username, labelprice.getText(), paymentCallback);
        	} else {
        	    JOptionPane.showMessageDialog(null, "Aready have a  booking. Please check your booking details.");
        	}
        }

        else if(e.getSource() == back){
            setVisible(false);
        }
        
    }
}
