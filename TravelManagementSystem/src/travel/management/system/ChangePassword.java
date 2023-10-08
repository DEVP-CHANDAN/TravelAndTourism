package travel.management.system;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChangePassword {
public static void main(String[] args) {
	showPopup(null);
}

public static void showPopup( String Username) {

	JFrame dialog = new JFrame("Change Password");
	 dialog.setSize(300, 150);
    dialog.setLayout(new FlowLayout());
    JLabel label1 = new JLabel("New Password");
    JTextField textField1 = new JTextField(15);

    JLabel label2 = new JLabel("Confirm Password");
    JTextField textField2 = new JTextField(15);

    JButton okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value1 = textField1.getText();
            String value2 = textField2.getText();

            if(value1.equals(value2)) {
            Conn c = new Conn();
            try {
            	String sql = "update account set password ='" + value1 + "' where username='"+Username+"'";
            	System.out.println(sql);
				c.s.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Updated");
				new Dashboard(Username);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            }
            else {
            	JOptionPane.showMessageDialog(null, "Type correct password");
            }
            dialog.dispose();
        }
    });

    dialog.add(label1);
    dialog.add(textField1);
    dialog.add(label2);
    dialog.add(textField2);
    dialog.add(okButton);

    dialog.setVisible(true);
}
}

