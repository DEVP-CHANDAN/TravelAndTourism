package travel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BookHotel extends JFrame implements ActionListener {

	private JDateChooser checkOutDateChooser;
	Choice chotel, cac, cfood;
	JTextField tfpersons, tfdays ,tfrooms;
	String username;
	JLabel labelusername, labelid, labelnumber, labelphone, labelprice ,labeldate;
	JButton checkprice, bookpackage, back;
	
	BookHotel(String username , String hotelname) {
		this.username = username;
		setBounds(250, 50, 1100, 650);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel text = new JLabel("BOOK HOTEL");
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

		JLabel lblpackage = new JLabel("Select Hotel");
		lblpackage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpackage.setBounds(40, 110, 150, 20);
		add(lblpackage);
		
		

		chotel = new Choice();
		chotel.setBounds(250, 110, 200, 20);
		add(chotel);

		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from hotel");
			while (rs.next()) {
				chotel.add(rs.getString("name"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(hotelname);
        chotel.select(hotelname);

        JLabel checkOutLabel = new JLabel("Booking Date");
        checkOutDateChooser = new JDateChooser();
        checkOutLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        checkOutLabel.setBounds(40, 150, 150, 20);
        checkOutDateChooser.setBounds(250, 150, 200, 20);
        add(checkOutLabel);
        add(checkOutDateChooser);
        
		JLabel lblperson = new JLabel("Total Person");
		lblperson.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblperson.setBounds(40, 190, 150, 25);
		add(lblperson);

		tfpersons = new JTextField("1");
		tfpersons.setBounds(250, 190, 200, 25);
		add(tfpersons);

		JLabel lbldays = new JLabel("No Of Days");
		lbldays.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldays.setBounds(40, 230, 150, 25);
		add(lbldays);

		tfdays = new JTextField("1");
		tfdays.setBounds(250, 230, 200, 25);
		add(tfdays);
		
		JLabel lblroom = new JLabel("Room");
		lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblroom.setBounds(40, 270, 150, 25);
		add(lblroom);

		tfrooms = new JTextField("1");
		tfrooms.setBounds(250, 270, 200, 25);
		add(tfrooms);

		JLabel lblac = new JLabel("AC/Non-AC");
		lblac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblac.setBounds(40, 310, 150, 25);
		add(lblac);

		cac = new Choice();
		cac.add("AC");
		cac.add("Non-AC");
		cac.setBounds(250, 310, 200, 20);
		add(cac);

		JLabel lbfood = new JLabel("Food Included");
		lbfood.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbfood.setBounds(40, 350, 150, 25);
		add(lbfood);

		cfood = new Choice();
		cfood.setBounds(250, 350, 200, 20);
		cfood.add("Yes");
		cfood.add("No");
		add(cfood);

		JLabel lblid = new JLabel("Id");
		lblid.setBounds(40, 390, 150, 25);
		lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblid);

		labelid = new JLabel();
		labelid.setBounds(250, 390, 150, 25);
		add(labelid);

		JLabel lblnumber = new JLabel("Number");
		lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblnumber.setBounds(40, 430, 150, 25);
		add(lblnumber);

		labelnumber = new JLabel();
		labelnumber.setBounds(250, 430, 150, 25);
		add(labelnumber);

		JLabel lblphone = new JLabel("Phone");
		lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblphone.setBounds(40, 470, 150, 20);
		add(lblphone);

		labelphone = new JLabel();
		labelphone.setBounds(250, 470, 150, 20);
		add(labelphone);
		
		

		JLabel lblprice = new JLabel("Total Price");
		lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblprice.setBounds(40, 510, 150, 20);
		add(lblprice);

		labelprice = new JLabel();
		labelprice.setBounds(250, 510, 200, 25);
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
		checkprice.setBounds(60, 560, 120, 25);
		add(checkprice);

		bookpackage = new JButton("Book Hotel");
		bookpackage.setBackground(Color.BLACK);
		bookpackage.addActionListener(this);
		bookpackage.setForeground(Color.WHITE);
		bookpackage.setBounds(200, 560, 120, 25);
		add(bookpackage);

		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.addActionListener(this);
		back.setForeground(Color.WHITE);
		back.setBounds(340, 560, 120, 25);
		add(back);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/book.jpg"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(i2);
		JLabel image = new JLabel(icon);
		image.setBounds(500, 70, 540, 400);
		add(image);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BookHotel("Chandan","Taj Lands End");
	}
	
	
	public boolean rommava(String name) {
		Conn c = new Conn();
		try {
			ResultSet rs = c.s.executeQuery("select * from hotel where name='"+name+"'");
			while(rs.next()) {
			int rooms = Integer.parseInt(rs.getString("rooms"));
			int room = Integer.parseInt(tfrooms.getText());
			if(room<rooms){
				return true;
			}
			else
			return false;
			}		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void reduceroom(String name) {
		Conn c = new Conn();
		Conn c1 = new Conn();
		try {
			ResultSet rs = c.s.executeQuery("select * from hotel where name='"+name+"'");
			if(cac.getSelectedItem() == "AC") {
			int rooms = 0;
			while(rs.next()) {
            rooms =Integer.parseInt(rs.getString("acroom"));
			}
            int room = Integer.parseInt(tfrooms.getText());
            int remainingrooms = rooms-room;
            String remainroom = remainingrooms+"";
            c1.s.executeUpdate("update hotel set acroom='"+remainroom+"' where name='"+name+"'");
			}
			else if(cac.getSelectedItem() == "Non-AC") {
				int rooms = 0 ;
				while(rs.next()) {
	            rooms =Integer.parseInt(rs.getString("rooms"));
				}
	            int room = Integer.parseInt(tfrooms.getText());
	            int remainingrooms = rooms-room;
	            String remainroom = remainingrooms+"";
	            c1.s.executeUpdate("update hotel set rooms='"+remainroom+"' where name='"+name+"'");
				}

			
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public boolean validBooking(String username) {
		Conn c = new Conn();
		boolean status = false;
		ResultSet rs = null;
		ResultSet rs1 = null;

		try {
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
			String formattedDateTimeString = currentDateTime.format(formatter1); 
			LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTimeString, formatter1); 

			System.out.println(parsedDateTime);

			System.out.println(parsedDateTime);

			rs = c.s.executeQuery("select * from bookhotel where username = '" + username + "'");
			Conn c1 = new Conn(); 
		    rs1 = c1.s.executeQuery("select * from bookhotel where username = '" + username + "'");

			if (rs1.next()) {
				while (rs.next()) {
					String timestamp = rs.getString("time");
					String day = rs.getString("days");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
					LocalDateTime newdate = dateTime.plusDays(Integer.parseInt(day));
					System.out.println(dateTime);
					System.out.println(newdate);

					if (parsedDateTime.isAfter(newdate)) {
						System.out.println(currentDateTime + " " + newdate);
						status = true;
					} else if (parsedDateTime.isBefore(newdate)) {
						status = false;
						
						
						//todo data should delete and store in backup table
					} else if (parsedDateTime.isAfter(newdate) || parsedDateTime.isEqual(newdate)) {
						try {
							Conn conn = new Conn();
							String sql = "INSERT INTO bookhotel (username, name, ac, food, days, persons, id, number, phone, price, time) VALUES ('"
									+ rs.getString("username") + "','" + rs.getString("name") + "','"
									+ rs.getString("ac") + "','" + rs.getString("food") + "','" + rs.getString("days")
									+ "','" + rs.getString("persons") + "','" + rs.getString("id") + "','"
									+ rs.getString("number") + "','" + rs.getString("phone") + "','"
									+ rs.getString("price") + "','" + rs.getString("time") + "')";
							String sql1 = "delete from bookhotel where username ='" + username + "'";
							conn.s.executeUpdate(sql);
							conn.s.executeUpdate(sql1);
							status = false;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkprice) {
			try {
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery("Select * from hotel where name= '" + chotel.getSelectedItem() + "' ");
				while (rs.next()) {
					int cost = Integer.parseInt(rs.getString("costperperson"));
					int food = Integer.parseInt(rs.getString("foodincluded"));
					int ac = Integer.parseInt(rs.getString("acroom"));

					int person = Integer.parseInt(tfpersons.getText());
					int days = Integer.parseInt(tfdays.getText());

					String acselected = cac.getSelectedItem();
					String foodselected = cfood.getSelectedItem();

					if (person * cost > 0) {
						int total = 0;
						total += acselected.equals("AC") ? ac : 0;
						total += foodselected.equals("Yes") ? food : 0;
						total += cost;
						total = total * person * days;
						labelprice.setText("Rs " + total);

					} else {
						JOptionPane.showMessageDialog(null, "Please enter a valid number");
					}
				}
			} catch (Exception e2) {
				System.out.println(e2);
			}
		} else if (e.getSource() == bookpackage) {
		    try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Conn c = new Conn();
		        boolean valid = validBooking(username);
		        boolean roomava = rommava(chotel.getSelectedItem());
		        Date selectedDate = checkOutDateChooser.getDate();
		        Date currentDate = new Date(); // Get today's date

		        // Compare the selected date with today's date
		        if (selectedDate.compareTo(currentDate) >= 0) {
		            if (valid && roomava) {
		                // Create a callback to handle payment completion
		                demo.PaymentCallback paymentCallback = new demo.PaymentCallback() {
		                    @Override
		                    public void onPaymentSuccessful(String totalAmount) {
		                        // Payment was successful, now insert data into the database
		                        try {
		                            String sql = "INSERT INTO bookhotel (username, name, ac, food, days, persons, id, number, phone, price, time, rooms, btime) VALUES ('"
		                                    + labelusername.getText() + "','" + chotel.getSelectedItem() + "','" + cac.getSelectedItem()
		                                    + "','" + cfood.getSelectedItem() + "','" + tfdays.getText() + "','" + tfpersons.getText()
		                                    + "','" + labelid.getText() + "','" + labelnumber.getText() + "','" + labelphone.getText()
		                                    + "','" + totalAmount + "','" + new Timestamp(System.currentTimeMillis()) + "','" + tfrooms.getText() + "','" + dateFormat.format(checkOutDateChooser.getDate()) + "')";
		                            c.s.executeUpdate(sql);
		                            reduceroom(chotel.getSelectedItem());
		                            JOptionPane.showMessageDialog(null, "Hotel booked Successfully");
		                        } catch (Exception e1) {
		                            e1.printStackTrace();
		                        }
		                    }
		                };

		                // Open the payment frame and pass the total amount and payment callback
		                new demo(username,labelprice.getText(), paymentCallback);
		            } else {
		                JOptionPane.showMessageDialog(null, "You have already one booking");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Invalid Date");
		        }

		        setVisible(false);
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
		}

		 else if (e.getSource() == back) {
			setVisible(false);
		}

	}
}
