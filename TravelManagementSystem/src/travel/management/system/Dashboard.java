package travel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    JButton addPersonalDetails,deletePersonalDetails , payment,viewBookedHotels ,destination,viewHotels, viewPersonalDetails,  bookHotels,updatePersonalDetails, ViewPackages, checkPackages, bookPackages;
    String username;

    public Dashboard(String username) {
        System.out.println("dashboard" + username);
        this.username = username;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 102));
        panel.setBounds(0, 0, 1366, 65);
        add(panel);

        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(50, 10, 300, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(heading);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 0, 102));
        panel1.setBounds(0, 65, 300, 900);
        add(panel1);

        addPersonalDetails = new JButton("Add Personal Details");
        addPersonalDetails.setBounds(0, 0, 300, 50);
        addPersonalDetails.setBackground(new Color(0, 0, 102));
        addPersonalDetails.addActionListener(this);
        addPersonalDetails.setForeground(Color.WHITE);
        addPersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addPersonalDetails.setMargin(new Insets(0, 0, 0, 60));
        panel1.add(addPersonalDetails);

        updatePersonalDetails = new JButton("Update Personal Details");
        updatePersonalDetails.setBounds(0, 50, 300, 50);
        updatePersonalDetails.setBackground(new Color(0, 0, 102));
        updatePersonalDetails.setForeground(Color.WHITE);
        updatePersonalDetails.addActionListener(this);
        updatePersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updatePersonalDetails.setMargin(new Insets(0, 0, 0, 30));
        panel1.add(updatePersonalDetails);

        viewPersonalDetails = new JButton("View Details");
        viewPersonalDetails.setBounds(0, 100, 300, 50);
        viewPersonalDetails.setBackground(new Color(0, 0, 102));
        viewPersonalDetails.addActionListener(this);
        viewPersonalDetails.setForeground(Color.WHITE);
        viewPersonalDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewPersonalDetails.setMargin(new Insets(0, 0, 0, 130));
        panel1.add(viewPersonalDetails);


        checkPackages = new JButton("Check Packages");
        checkPackages.setBounds(0, 150, 300, 50);
        checkPackages.setBackground(new Color(0, 0, 102));
        checkPackages.addActionListener(this);
        checkPackages.setForeground(Color.WHITE);
        checkPackages.setFont(new Font("Tahoma", Font.PLAIN, 20));
        checkPackages.setMargin(new Insets(0, 0, 0, 110));
        panel1.add(checkPackages);

        bookPackages = new JButton("Book Packages");
        bookPackages.setBounds(0, 200, 300, 50);
        bookPackages.setBackground(new Color(0, 0, 102));
        bookPackages.setForeground(Color.WHITE);
        bookPackages.addActionListener(this);
        bookPackages.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookPackages.setMargin(new Insets(0, 0, 0, 120));
        panel1.add(bookPackages);

        ViewPackages = new JButton("View Packages");
        ViewPackages.setBounds(0, 250, 300, 50);
        ViewPackages.setBackground(new Color(0, 0, 102));
        ViewPackages.setForeground(Color.WHITE);
        ViewPackages.addActionListener(this);
        ViewPackages.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ViewPackages.setMargin(new Insets(0, 0, 0, 120));
        panel1.add(ViewPackages);

        viewHotels = new JButton("View Hotel");
        viewHotels.setBounds(0, 300, 300, 50);
        viewHotels.setBackground(new Color(0, 0, 102));
        viewHotels.addActionListener(this);
        viewHotels.setForeground(Color.WHITE);
        viewHotels.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewHotels.setMargin(new Insets(0, 0, 0, 150));
        panel1.add(viewHotels);

        bookHotels = new JButton("Book Hotel");
        bookHotels.setBounds(0, 350, 300, 50);
        bookHotels.addActionListener(this);
        bookHotels.setBackground(new Color(0, 0, 102));
        bookHotels.setForeground(Color.WHITE);
        bookHotels.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bookHotels.setMargin(new Insets(0, 0, 0, 150));
        panel1.add(bookHotels);

        viewBookedHotels = new JButton("View Booked Hotel");
        viewBookedHotels.setBounds(0, 400, 300, 50);
        viewBookedHotels.setBackground(new Color(0, 0, 102));
        viewBookedHotels.addActionListener(this);
        viewBookedHotels.setForeground(Color.WHITE);
        viewBookedHotels.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewBookedHotels.setMargin(new Insets(0, 0, 0, 80));
        panel1.add(viewBookedHotels);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("images/home.jpg"));
        Image i5 = i4.getImage().getScaledInstance(1600, 1000, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(0, 0, 1600, 1000);
        add(image1);
        JLabel text = new JLabel("Travel And Tourism");
        text.setBounds(600, 70, 1000, 70);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.PLAIN, 55));
        image1.add(text);
        System.out.println(username);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonalDetails) {
            new AddCustomer(username);
        } else if (e.getSource() == viewPersonalDetails) {
            new ViewCustomer(username);
        } else if (e.getSource() == updatePersonalDetails) {
            new UpdateCustomer(username);
        } else if (e.getSource() == checkPackages) {
            new CheckPackage();
        } else if (e.getSource() == bookPackages) {
            new BookPackage(username);
        } else if (e.getSource() == ViewPackages) {
            new ViewPackage(username);
        } else if (e.getSource() == viewHotels) {
            new CheckHotel(username);
        }
        else if(e.getSource() ==  bookHotels) {
        	new BookHotel(username,"");
        }
        else if(e.getSource() == viewBookedHotels ) {
        	new ViewBookedHotel(username);
        }
    }

    public static void main(String[] args) {
        new Dashboard("");
    }

}
