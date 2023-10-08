package travel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class UpdateCustomer extends JFrame implements ActionListener {

    JLabel labelusername, labelname;
    JComboBox comboid;
    JTextField tfnumber, tfcountry, tfaddress, tfemail, tfgender, tfphone, tfid;
    JButton add, back;

    UpdateCustomer(String username) {
        setBounds(350, 100, 850, 550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("UPDATE CUSTOMER DETAILS");
        text.setBounds(50, 0, 300, 25);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(30, 90, 150, 25);
        add(lblid);

        tfid = new JTextField();
        tfid.setBounds(220, 90, 150, 25);
        add(tfid);

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

        tfgender = new JTextField();
        tfgender.setBounds(220, 210, 150, 25);
        add(tfgender);

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

        add = new JButton("Update");
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

        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("images/update.png"));
        Image i2 = i.getImage().getScaledInstance(250, 400, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(i2);
        JLabel image = new JLabel(icon);
        image.setBounds(400, 40, 450, 420);
        add(image);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where username = '" + username + "'");
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
                tfid.setText(rs.getString("id"));
                tfnumber.setText(rs.getString("number"));
                tfgender.setText(rs.getString("gender"));
                tfcountry.setText(rs.getString("country"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
        }

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String username = labelusername.getText();
            String id = tfid.getText();
            String name = labelname.getText();
            String number = tfnumber.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            String country = tfcountry.getText();
            String address = tfaddress.getText();
            String gender = tfgender.getText();
            try {
                Conn c = new Conn();
                c.s.executeUpdate("UPDATE customer set username='" + username + "',id = '" + id + "',number='" + number + "',name='" + name + "',gender='" + gender + "',country='" + country + "',address='" + address + "',phone='" + phone + "',email='" + email + "' where username='"+username+"'");
                JOptionPane.showMessageDialog(null, "Customer Updated");
                setVisible(false);
            } catch (Exception e1) {
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Dashboard(" ");
        }
    }

    public static void main(String[] args) {
        new UpdateCustomer("");

    }

}
