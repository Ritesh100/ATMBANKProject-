package atmjava;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DefaultPage{
	
	static void startActivity() {
		JFrame frame = new JFrame();
		JDBCConnection jdbc = new JDBCConnection();
		Font  welcome_font  = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35);
		Font  normal_font  = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16);
		JPanel panel = new JPanel();
 
		panel.setLayout(null);
				
		JLabel label = new JLabel("Aadim Bank", SwingConstants.CENTER);
		label.setBackground(Color.PINK);
		label.setBounds(0, 0, 790, 80);
		label.setFont(welcome_font);
		panel.add(label);
		
		JLabel select_service = new JLabel("Available services: ");
		select_service.setHorizontalAlignment(SwingConstants.CENTER);
		select_service.setBounds(0, 92, 790, 33);
		select_service.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		panel.add(select_service);
		
		
		
		JButton deposit, atm_service, account_opening, account_login;
		
		deposit = new JButton("Deposit Money");
		deposit.setFont(normal_font);;
		deposit.setBounds(18, 156, 262, 100);
		panel.add(deposit);
		
		atm_service = new JButton("ATM Service");
		deposit.setFont(normal_font);
		atm_service.setBounds(18, 298, 262, 100);
		panel.add(atm_service);
		
		account_opening = new JButton("New Account");
		deposit.setFont(normal_font);
	
		account_opening.setBounds(508, 156, 262, 100);
		panel.add(account_opening);
		
		account_login = new JButton("Account Login");
		deposit.setFont(normal_font);
		account_login.setBounds(508, 298, 262, 100);
		panel.add(account_login);
		
		deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	DepositMoney.depositMoneyPanel(jdbc, frame);	
			}});
	
		atm_service.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	WelcomeScreenATM.atmWelcomeScreenPane(jdbc, frame);			
			}});
		
		account_opening.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	OpenBankAccount.openBankAccountPanel(jdbc, frame);		
			}});
		
		account_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	AccountLogin.accountLogin(jdbc, frame);
			}});
		
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 790, 80);
		panel.add(panel_1);
		
		
		frame.setResizable(true);
		frame.setSize(796, 484);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		startActivity();
	}
}

