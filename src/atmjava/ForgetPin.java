package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ForgetPin {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void forgotPinPanel(String atmNumber, JDBCConnection jdbc, JFrame frame) {
		JLabel guide_text, enter_email, title;
		JButton sendOtp, cancel;
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		JTextField emailEnterInput;
		panel.setLayout(null);
		
		JLabel lblHamroRamroBank = new JLabel("Hamro Ramro Bank");
		lblHamroRamroBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblHamroRamroBank.setHorizontalAlignment(SwingConstants.CENTER);
		lblHamroRamroBank.setBounds(0, 0, 586, 73);
		panel.add(lblHamroRamroBank);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 586, 73);
		panel.add(panel_1);
		
		title = new JLabel("Forgot Pin", SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		title.setForeground(Color.CYAN);
		title.setBounds(156, 85, 300, 50);
		
		guide_text = new JLabel("ATM Number: " + atmNumber);
		guide_text.setHorizontalAlignment(SwingConstants.CENTER);
		guide_text.setBounds(0, 131, 586, 50);
		
		enter_email = new JLabel("Please enter your email address:");
		enter_email.setForeground(Color.CYAN);
		enter_email.setBounds(40, 180, 500, 50);
		
		emailEnterInput = new JTextField("");
		emailEnterInput.setBounds(40, 230, 355, 30);
		
		sendOtp = new JButton("Send OTP");
		sendOtp.setForeground(Color.white);
		sendOtp.setBackground(Color.CYAN);
		sendOtp.setBounds(407, 229, 152, 30);
		
		sendOtp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailEnterInput.getText().toString().trim();
				if(email.isEmpty()) {
					JOptionPane.showMessageDialog(frame,
						    "Email not valid",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				} else {
					String getEmail = jdbc.getData("SELECT email FROM user WHERE user_id=(SELECT user_id FROM atm_users WHERE atm_number ='"+atmNumber+"')");
					if(getEmail.equals(email) ) {
						Random rand = new Random();
						String defCode = "";
						for (int i = 0; i < 6; i++) {
						        int n = rand.nextInt(10) + 0;
						        defCode += Integer.toString(n);
						}
						String name = getName(atmNumber, jdbc);
						boolean status = SendMail.mailSend(getEmail, name, defCode);
						if(status) {
							JOptionPane.showMessageDialog(frame,
								    "OTP code sent to email",
								    "Email Sent",
								    JOptionPane.INFORMATION_MESSAGE);
							frame.getContentPane().removeAll();
							frame.repaint();
							long mailSentTime = System.currentTimeMillis();
							EnterOTP.enterOtpScreenPane(atmNumber, jdbc, frame, defCode, mailSentTime);
						} else {
							JOptionPane.showMessageDialog(frame,
								    "Error sending email",
								    "Error",
								    JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frame,
							    "Email doesn't match with the one\nassociated to this acccount",
							    "Error",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			}});
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.white);
		cancel.setBackground(Color.red);
		cancel.setBounds(40, 290, 100, 30);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				PinInput.pinInputPanel(jdbc, atmNumber, frame);
			}});
		
		panel.add(title);
		panel.add(guide_text);
		panel.add(enter_email);
		panel.add(emailEnterInput);
		panel.add(sendOtp);
		panel.add(cancel);
		
		frame.getContentPane().add(panel);
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static String getName(String atmNumber, JDBCConnection jdbc) {
		String name = null;
		String firstName = jdbc.getData("SELECT first_name FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		String lastName = jdbc.getData("SELECT last_name FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		name = firstName + ' ' + lastName;
		return name;
	}
}

