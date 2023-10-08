package travel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CheckOtp extends JFrame {

	private JTextField textField;
    private JButton button;
    static String username = null;
    public CheckOtp(String Username) {
    	username = Username;
        // Set frame properties
        setTitle("OTP VERIFY");
        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        textField = new JTextField(20);
        button = new JButton("CHECK");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Enter OTP: "));
        panel.add(textField);
        panel.add(button);

        getContentPane().add(panel);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int text = Integer.parseInt(textField.getText());
                Mailer otp = new Mailer();
                if(otp.getOTP() == text) {
                	ChangePassword obj = new ChangePassword();
                	obj.showPopup(Username);
                }
                else {
                	JOptionPane.showMessageDialog(null, "Wrong OTP");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CheckOtp popupFrame = new CheckOtp(username);
            }
        });
    }
}
