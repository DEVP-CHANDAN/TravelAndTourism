package travel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CheckHotel extends JFrame{
    JButton book;
    String Username;;
    public CheckHotel(String username) {
        JFrame frame = new JFrame("Check Hotel");
        frame.setBounds(350, 50, 1000, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        Conn c = new Conn();
        try {
            ResultSet rs = c.s.executeQuery("select * from hotel");
            List<String[]> listofArray = createStringArray(rs);

            for (int i = 0; i < listofArray.size(); i++) {
                JPanel panel = createPackage(listofArray.get(i));
                tabbedPane.addTab("Package " + (i + 1), null, panel);
                String hotel = listofArray.get(i)[0];
                book = new JButton("Book Now");
                book.setBounds(200, 430, 100, 30);
                book.setForeground(Color.white);
                book.setBackground(Color.black);
                book.addActionListener(new TabButtonActionListener (username,hotel ));
                panel.add(book);
            }
            frame.add(tabbedPane);
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> createStringArray(ResultSet rs) {
        List<String[]> array = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String cost = rs.getString("costperperson");
                String ac = rs.getString("acroom");
                String food = rs.getString("foodincluded");
                String rating = rs.getString("rating");
                String days = rs.getString("days");
                String person = rs.getString("persons");
                String beds = rs.getString("beds");
                String rooms = rs.getString("rooms");
                String image = rs.getString("image");
                String[] newArray = new String[]{name, ac,rooms, food, rating, days, person, beds,cost,image};
                array.add(newArray);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public JPanel createPackage(String[] pack) {
    	 JPanel panel = new JPanel();
         panel.setLayout(null);
         panel.setBackground(Color.WHITE);
          
         JLabel l1 = new JLabel(pack[0]);
         l1.setBounds(50, 5, 600, 30);
         l1.setForeground(Color.black);
         l1.setFont(new Font("Tahoma", Font.BOLD, 30));
         panel.add(l1);

         JLabel l2 = new JLabel("No Of AC Rooms: "+pack[1]);
         l2.setBounds(30, 60, 400, 30);
         l2.setForeground(Color.black);
         l2.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l2);

         JLabel l3 = new JLabel("No Of Rooms: "+pack[2]);
         l3.setBounds(30, 110, 300, 30);
         l3.setForeground(Color.black);
         l3.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l3);

         JLabel l4 = new JLabel("Types Of Food Available: "+pack[3]);
         l4.setBounds(30, 160, 600, 30);
         l4.setForeground(Color.black);
         l4.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l4);

         JLabel l5 = new JLabel("Our Rating: "+pack[4]+"%");
         l5.setBounds(30, 210, 300, 30);
         l5.setForeground(Color.black);
         l5.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l5);

         JLabel l6 = new JLabel("No. Of Days: "+(pack[5]));
         l6.setBounds(30, 260, 600, 30);
         l6.setForeground(Color.black);
         l6.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l6);

         JLabel l7 = new JLabel("For persons: " + pack[6]);
         l7.setBounds(30, 310, 600, 30);
         l7.setForeground(Color.black);
         l7.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l7);
         
         JLabel l8 = new JLabel("No Of Beds: "+pack[7]);
         l8.setBounds(30, 350, 300, 30);
         l8.setForeground(Color.black);
         l8.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l8);

         JLabel l9 = new JLabel("Rs./" + pack[8]);
         l9.setBounds(60, 430, 300, 30);
         l9.setForeground(Color.black);
         l9.setFont(new Font("Tahoma", Font.BOLD, 25));
         panel.add(l9);
 
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/"+pack[9]+".jpg"));
         Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
         ImageIcon icon = new ImageIcon(i2);
         JLabel image = new JLabel(icon);
         image.setBounds(450, 50, 500, 300);
         panel.add(image);

         return panel;
    }

    public static void main(String[] args) {
        new CheckHotel("SS");
    }

	
		
	}


class TabButtonActionListener implements ActionListener {
	private String username;
	private String hotel;


    public TabButtonActionListener(String username,String hotel ) {
        this.username = username;
        this.hotel = hotel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
			new BookHotel(username , hotel);
		}

}


