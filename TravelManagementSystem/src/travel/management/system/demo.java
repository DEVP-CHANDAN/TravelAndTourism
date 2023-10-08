package travel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class demo extends JFrame {
    private JTextField amountField;
    private PaymentCallback paymentCallback;
    String username;
    
    public demo(String username , String totalAmount, PaymentCallback callback) {
        this.paymentCallback = callback; 
        this.username = username;  
        setTitle("Payment");
        setSize(400, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(new Color(240, 240, 240)); 

        // Create and configure components
        JLabel titleLabel = new JLabel("Demo Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel amountLabel = new JLabel("Total Amount:");
        amountField = new JTextField(totalAmount, 10);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField(16); 

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(30);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(30, 144, 255));
        payButton.setForeground(Color.WHITE); 
        payButton.setBounds(160, 160, 80, 30);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean paymentSuccessful = simulatePayment();
                if (paymentSuccessful) {
                    String paymentAmount = amountField.getText();
                    String cardNumber = cardNumberField.getText();
                    String email = emailField.getText();

                    
                    insertPaymentDetails(paymentAmount, cardNumber, email);

                    
                    paymentCallback.onPaymentSuccessful(amountField.getText());

                    JOptionPane.showMessageDialog(demo.this, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(demo.this, "Payment Failed. Please try again.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
       
        JPanel panel = new JPanel();
        panel.setLayout(null);

        
        titleLabel.setBounds(80, 10, 240, 30);
        panel.add(titleLabel);

        amountLabel.setBounds(20, 50, 100, 30);
        panel.add(amountLabel);

        cardNumberLabel.setBounds(20, 80, 100, 30);
        panel.add(cardNumberLabel);

        emailLabel.setBounds(20, 110, 100, 30);
        panel.add(emailLabel);

        amountField.setBounds(140, 50, 200, 30);
        panel.add(amountField);

        cardNumberField.setBounds(140, 80, 200, 30);
        panel.add(cardNumberField);

        emailField.setBounds(140, 110, 200, 30);
        panel.add(emailField);

        payButton.setBounds(160, 160, 80, 30);
        panel.add(payButton);

        
        getContentPane().add(panel, BorderLayout.CENTER);

        
        setVisible(true);
    }
    
    private void insertPaymentDetails(String paymentAmount, String cardNumber, String email) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Conn c = new Conn();
            
            String sql = "INSERT INTO payments (username, payment_amount, payment_date, card_number, email) VALUES ('" + username + "','" + paymentAmount + "','" + dateFormat.format(new Date()) + "','" + cardNumber + "','" + email + "')";
            c.s.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean simulatePayment() {
        
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to proceed with the payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    public interface PaymentCallback {
        void onPaymentSuccessful(String totalAmount);
    }
}
