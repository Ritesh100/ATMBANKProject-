package atmjava;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.Random;
import java.util.Base64.Encoder;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AcountDetails {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void accountDetailsPane(JDBCConnection jdbc, JFrame frame, String email) {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JButton logout, requestATM;
		
		JLabel accNumber, name, address, dateOfBirth, emailAddress, gender, maritialStatus, profilePhoto, balance, accountBalance, accountNumber, atmNumber;
		
		ImageIcon icon = jdbc.getImage("SELECT photo FROM user WHERE email='"+email+"'");
		profilePhoto = new JLabel();
		profilePhoto.setBounds(32, 113, 178, 155);
		profilePhoto.setBorder(BorderFactory.createLineBorder(Color.black));
		profilePhoto.setIcon(icon);
		
		String displayName = getName(jdbc, email);
		
		name = new JLabel("Full Name: "+displayName);
		name.setBounds(254, 101, 372, 30);
		
		String birthDate = jdbc.getData("SELECT dob FROM user WHERE email='"+email+"'");
		dateOfBirth = new JLabel("Date of Birth: " + birthDate);
		dateOfBirth.setBounds(254, 143, 372, 30);
		
		String addressStored = jdbc.getData("SELECT address FROM user WHERE email='"+email+"'");
		address = new JLabel("Address: " + addressStored);
		address.setBounds(254, 185, 372, 30);
		
		String genderStored = jdbc.getData("SELECT gender FROM user WHERE email='"+email+"'");
		gender = new JLabel("Gender: " + genderStored);
		gender.setBounds(254, 224, 372, 30);
		
		String marriageStatus = jdbc.getData("SELECT marriagestatus FROM user WHERE email='"+email+"'");
		maritialStatus = new JLabel("Marritial Status: " + marriageStatus);
		maritialStatus.setBounds(254, 266, 372, 30);
		
		emailAddress = new JLabel("Email: " + email);
		emailAddress.setBounds(254, 308, 372, 30);
		
		balance = new JLabel("Balance: ");
		balance.setBounds(254, 350, 100, 30);
		
		String accBalance = jdbc.getData("SELECT balance FROM user WHERE email = '"+email+"'");
		accountBalance = new JLabel("Rs. " + accBalance);
		accountBalance.setBounds(334, 350, 100, 30);
		
		accNumber = new JLabel("Account Number: ");
		accNumber.setBounds(254, 392, 150, 30);
		
		String accNum = jdbc.getData("SELECT account_number FROM user WHERE email='"+email+"'");
		accountNumber = new JLabel(accNum);
		accountNumber.setBounds(380, 392, 100, 30);
		requestATM = new JButton("Request ATM");

		requestATM.setBounds(254, 422,150, 30);
		
		String isUsingATMService = jdbc.getData("SELECT atm_service FROM user WHERE email = '"+email+"'");
		if(isUsingATMService.equals("0")) {
			atmNumber = new JLabel("ATM Service: NO");
			atmNumber.setBounds(380, 422, 100, 30);
			panel.add(requestATM);
		} else {
			String getATMNumber = jdbc.getData("SELECT atm_number FROM atm_users WHERE user_id = (SELECT user_id FROM user WHERE email = '"+email+"')");
			atmNumber = new JLabel("ATM Number: " + getATMNumber);
			atmNumber.setBounds(254, 442, 500, 30);
		}
		
		requestATM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 frame.getContentPane().removeAll();
				 frame.repaint();
				 panel.removeAll();
				 Random rand = new Random();
					String defCode = "";
					boolean isUnique = false;
					do {
						for (int i = 0; i < 6; i++)
					    {
					        int n = rand.nextInt(10) + 0;
					        defCode += Integer.toString(n);
					    }
						isUnique = ifExistsCode(defCode, jdbc);
					} while(isUnique == false);
					String pin = "";
					for (int i = 0; i < 4; i++)
				    {
				        int n = rand.nextInt(10) + 0;
				        pin += Integer.toString(n);
				    }
					int u_id = jdbc.getDataInt("SELECT user_id FROM user WHERE email = '"+email+"'");
				
					jdbc.executeQuery("UPDATE user SET atm_service = true WHERE email = '"+email+"'");
					jdbc.executeQuery("INSERT INTO atm_users(user_id, atm_number, pin) VALUES('"+u_id+"','"+defCode+"','"+pin+"')");
					JOptionPane.showMessageDialog(null,
						    "ATM Number: "+defCode+"\nDefault Pin: "+pin+"",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
				 AcountDetails.accountDetailsPane(jdbc, frame, email);
			}
			
		});
		
		logout = new JButton("Logout");
	
		logout.setBounds(72, 286, 100, 30);
		
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				jdbc.closeConnection();
				DefaultPage.startActivity();
			}});
		
		panel.add(name);
		panel.add(profilePhoto);
		panel.add(dateOfBirth);
		panel.add(address);
		panel.add(gender);
		panel.add(maritialStatus);
		panel.add(emailAddress);
		panel.add(balance);
		panel.add(accountBalance);
		panel.add(accNumber);
		panel.add(accountNumber);
		panel.add(atmNumber);
		panel.add(logout);
		
		frame.getContentPane().add(panel);
		
		JLabel AadimBank = new JLabel("Aadim Bank");
		AadimBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		AadimBank.setHorizontalAlignment(SwingConstants.CENTER);
		AadimBank.setBounds(0, 0, 638, 73);
		panel.add(AadimBank);
		
		JPanel panel_1 = new JPanel();

		panel_1.setBounds(0, 0, 638, 73);
		panel.add(panel_1);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static boolean ifExistsCode(String code, JDBCConnection jdbc) {
		boolean status;
		int count = jdbc.getDataInt("SELECT COUNT(*) FROM atm_users WHERE atm_number = '"+code+"'");
		if(count == 0) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}

	static String getName(JDBCConnection jdbc, String email) {
		// TODO Auto-generated method stub
		String name = null;
		String title = jdbc.getData("SELECT title FROM user WHERE email='"+email+"'");
		String firstName = jdbc.getData("SELECT first_name FROM user WHERE email='"+email+"'");
		String lastName = jdbc.getData("SELECT last_name FROM user WHERE email='"+email+"'");
		name = title + " " + firstName + " " + lastName;
		return name;
	}
}

