package atmjava;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	private static String myAccountEmail = "reteshthapakaji@gmail.com";
	private static String myAccountPassword = "";

	static boolean mailSend(String receipent, String name , String otpCode) {
		boolean status;
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, myAccountPassword);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, receipent, name, otpCode);
		
		try {
			Transport.send(message);
			status = true;
		} catch (MessagingException e) {
			status = false;
			e.printStackTrace();
		}		
		
		return status;
	}
	
	private static Message prepareMessage(Session session, String email, String receipent, String name, String otpCode) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
			message.setSubject("Reset ATM PIN");
			String htmlCode = "<img src=\"https://902558.smushcdn.com/2161880/wp-content/uploads/2020/08/man-forgot-his-password_173125-93-626x375.jpg?lossy=1&strip=1&webp=1\" alt=\"Forgot Password Image\"><br/>"
					+ "<h3>Hello, '"+name+"'</h3><p>Please use the following OTP code to reset your ATM pin:</p><h3 style=\"text-align:center;\">'"+otpCode+"'</h3><p>Use this code within 2 minutes or else it will expire</p><br/><p>Best Regards,</p><p>Aadim Bank</p>";
			message.setContent(htmlCode, "text/html");
			return message;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
