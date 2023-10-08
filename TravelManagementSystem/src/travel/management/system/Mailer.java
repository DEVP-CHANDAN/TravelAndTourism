package travel.management.system;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
	
	public static int OTP = 0;
    public static void main(String[] args) {
    
    }
    
    public Mailer() {
	}
    
   public Mailer(String to, String from, String host, String username, String password){
	   Properties properties = System.getProperties();
       properties.setProperty("mail.smtp.host", host);
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.port", "587");

       Session session = Session.getDefaultInstance(properties, new Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
           }
       });
       


       try {
           MimeMessage message = new MimeMessage(session);


           message.setFrom(new InternetAddress(from));


           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


           message.setSubject("PASSWORD RECOVERY");
               
            int otp = generateRandom4DigitNumber();
            OTP = otp;

           message.setText("OTP TO VERIFY :--" +otp+"");


           Transport.send(message);
           System.out.println("Email sent successfully!");
       } catch (MessagingException mex) {
           mex.printStackTrace();
       }
    }
    
    public static int generateRandom4DigitNumber() {
        Random rand = new Random();
        int min = 1000; 
        int max = 9999; 


        return rand.nextInt(max - min + 1) + min;
    }
    
    public int getOTP() {
    	return OTP;
    }

   
    
 
}
