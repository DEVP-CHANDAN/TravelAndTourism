package travel.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AddCustomer extends JFrame implements ActionListener {

    JLabel labelusername, labelname;
    JComboBox comboid;
    JTextField tfnumber, tfcountry, tfaddress, tfemail, tfphone;
    JRadioButton rmale, rfemale;
    JButton add, back;

    AddCustomer(String username) {
        System.out.println(username);
        setBounds(350, 100, 850, 550);
        setTitle("Add Personal Details");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(30, 90, 150, 25);
        add(lblid);

        comboid = new JComboBox(new String[]{"Passport", "Aadhar Card", "Pan Card", "Ration Card"});
        comboid.setBounds(220, 90, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 130, 150, 25);
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(220, 130, 150, 35);
        add(tfnumber);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 170, 150, 25);
        add(name);

        labelname = new JLabel();
        labelname.setBounds(220, 170, 150, 25);
        add(labelname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 210, 150, 25);
        add(lblgender);

        rmale = new JRadioButton("Male");
        rmale.setBounds(220, 210, 70, 25);
        rmale.setBackground(Color.WHITE);
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBounds(300, 210, 70, 25);
        rfemale.setBackground(Color.WHITE);
        add(rfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(30, 250, 150, 25);
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(220, 250, 150, 35);
        add(tfcountry);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 290, 150, 25);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 290, 150, 35);
        add(tfaddress);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30, 330, 150, 25);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(220, 330, 150, 35);
        add(tfemail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 370, 150, 25);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 370, 150, 35);
        add(tfphone);

        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.addActionListener(this);
        add.setForeground(Color.WHITE);
        add.setBounds(70, 430, 100, 25);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBounds(220, 430, 100, 25);
        add(back);

        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("images/newcustomer.jpg"));
        Image i2 = i.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(i2);
        JLabel image = new JLabel(icon);
        image.setBounds(400, 40, 450, 420);
        add(image);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from account where username = '"+ username+"'");
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
            }
        } catch (Exception e) {
        }

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource() == add){
             String username = labelusername.getText();
             String name = labelname.getText();
             String id = (String) comboid.getSelectedItem();
             String number = tfnumber.getText();
             String email = tfemail.getText();
             String phone = tfphone.getText();
             String country = tfcountry.getText();
             String address = tfaddress.getText();
            String gender = null;
            if(rmale.isSelected()){
            gender = "Male";}
            else{
               gender = "Female";
            }
         
             try {
                 Conn c = new Conn();
                 c.s.executeUpdate("INSERT INTO customer VALUES ('" + username + "','" + id + "','" + number + "','" + name + "','" + gender + "','" + country + "','" + address + "','" + phone + "','" + email + "')");
                 JOptionPane.showMessageDialog(null, "Customer Created" );
                 setVisible(false);
             } catch (Exception e1) {
             }
             
         }
         else if(e.getSource() == back){
             setVisible(false);
             new Dashboard(" ");
         }
    }

    public static void main(String[] args) {
        new AddCustomer("SS");

    }

}
