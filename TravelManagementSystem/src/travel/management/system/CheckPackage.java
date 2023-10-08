package travel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CheckPackage extends JFrame {

    public CheckPackage() {
        JFrame frame = new JFrame("Packages Available");
        frame.setBounds(350, 100, 900, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        Conn c = new Conn();
        try {
            ResultSet rs = c.s.executeQuery("select * from package");
            List<String[]> listofArray = createStringArray(rs);

            for (int i = 0; i < listofArray.size(); i++) {
                JPanel panel = createPackage(listofArray.get(i));
                tabbedPane.addTab("Package " + (i + 1), null, panel);
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
                String feature1 = rs.getString("feature1");
                String feature2 = rs.getString("feature2");
                String feature3 = rs.getString("feature3");
                String feature4 = rs.getString("feature4");
                String feature5 = rs.getString("feature5");
                String feature6 = rs.getString("feature6");
                String feature7 = rs.getString("feature7");
                String price = rs.getString("price");
                String image = rs.getString("image");

                String[] newArray = new String[]{name, feature1, feature2, feature3, feature4, feature5, feature6, feature7, price,image};
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
         l1.setBounds(50, 5, 300, 30);
         l1.setForeground(Color.black);
         l1.setFont(new Font("Tahoma", Font.BOLD, 30));
         panel.add(l1);

         JLabel l2 = new JLabel(pack[1]);
         l2.setBounds(30, 60, 300, 30);
         l2.setForeground(Color.black);
         l2.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l2);

         JLabel l3 = new JLabel(pack[2]);
         l3.setBounds(30, 110, 300, 30);
         l3.setForeground(Color.black);
         l3.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l3);

         JLabel l4 = new JLabel(pack[3]);
         l4.setBounds(30, 160, 300, 30);
         l4.setForeground(Color.black);
         l4.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l4);

         JLabel l5 = new JLabel(pack[4]);
         l5.setBounds(30, 210, 300, 30);
         l5.setForeground(Color.black);
         l5.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l5);

         JLabel l6 = new JLabel(pack[5]);
         l6.setBounds(30, 260, 300, 30);
         l6.setForeground(Color.black);
         l6.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l6);

         JLabel l7 = new JLabel(pack[6]);
         l7.setBounds(30, 310, 300, 30);
         l7.setForeground(Color.black);
         l7.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l7);

         JLabel l8 = new JLabel(pack[7]);
         l8.setBounds(30, 360, 300, 30);
         l8.setForeground(Color.black);
         l8.setFont(new Font("Tahoma", Font.BOLD, 20));
         panel.add(l8);

         JLabel l9 = new JLabel("Rs./" + pack[8]);
         l9.setBounds(60, 430, 300, 30);
         l9.setForeground(Color.black);
         l9.setFont(new Font("Tahoma", Font.BOLD, 25));
         panel.add(l9);


         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/" + pack[9]));
         Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
         ImageIcon icon = new ImageIcon(i2);
         JLabel image = new JLabel(icon);
         image.setBounds(350, 20, 500, 300);
         panel.add(image);

         return panel;
    }

    public static void main(String[] args) {
        new CheckPackage();
    }
}

