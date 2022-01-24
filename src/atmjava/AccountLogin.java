package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AccountLogin {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void accountLogin(JDBCConnection jdbc, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel enter_email, enter_password;
		JPasswordField passwordInput;
		JTextField emailInput;
		JButton cancel, login;
		
		JLabel AadimBank = new JLabel("Aadim Bank");
		AadimBank.setBounds(0, 0, 638, 73);
		panel.add(AadimBank);
		AadimBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		AadimBank.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
	
		panel_1.setBounds(0, 0, 638, 73);
		panel.add(panel_1);
		
		
		enter_email = new JLabel("Enter your email address");
		enter_email.setHorizontalAlignment(SwingConstants.CENTER);
		enter_email.setBounds(0, 107, 638, 30);
		
		emailInput = new JTextField("");
		emailInput.setBounds(221, 149, 212, 30);
		
		enter_password = new JLabel("Enter password");
		enter_password.setHorizontalAlignment(SwingConstants.CENTER);
		enter_password.setBounds(0, 193, 638, 30);
		
		passwordInput = new JPasswordField("");
		passwordInput.setBounds(221, 234, 212, 30);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.BLACK);
		cancel.setBackground(Color.red);
		cancel.setBounds(333, 283, 100, 30);
		
		login = new JButton("Login");
		login.setBounds(221, 283, 100, 30);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				jdbc.closeConnection();
				DefaultPage.startActivity();
			}});
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String email = emailInput.getText().toString().toLowerCase().trim();
				String pass = new String(passwordInput.getPassword());
				int emailValue = jdbc.getDataInt("SELECT COUNT(*) FROM user WHERE email='"+email+"'");
				if(emailValue == 1) {
					String passwordStored = jdbc.getData("SELECT password FROM user WHERE email='"+email+"'");
					if(pass.equals(passwordStored)) {
						frame.getContentPane().removeAll();
			        	frame.repaint();
			        	panel.removeAll();
			        	AcountDetails.accountDetailsPane(jdbc, frame, email);
					} else {
						showError("Either email or password is wrong");
					}
				} else {
					showError("Either email or password is wrong");
				}
			}});
		
		panel.add(enter_email);
		panel.add(emailInput);
		panel.add(enter_password);
		panel.add(passwordInput);
		panel.add(cancel);
		panel.add(login);		
		
		frame.getContentPane().add(panel);
		
		frame.setSize(500, 350);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static void showError(String message) {
		JOptionPane.showMessageDialog(null,
			    message,
			    "Error",
			    JOptionPane.WARNING_MESSAGE);	
	}
}
