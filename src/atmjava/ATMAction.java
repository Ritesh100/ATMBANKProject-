package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ATMAction {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void atmActionPanel(JDBCConnection jdbc, String atmNumber, JFrame frame) {
		//Definitions
		JPanel panel = new JPanel();
		JButton exit, fastCashBtn, withdrawBtn, miniStatementBtn, balanceEnquiryBtn, pinChange;
		panel.setLayout(null);
		
		JLabel welcome_user = new JLabel("Select Your Transaction", SwingConstants.CENTER);
		welcome_user.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		welcome_user.setBounds(146, 101, 300, 35);
		
		fastCashBtn = new JButton("Fast Cash");
		fastCashBtn.setBackground(Color.CYAN);
		fastCashBtn.setBounds(40, 184, 200, 30);
		
		withdrawBtn = new JButton("Withdraw");
		withdrawBtn.setBackground(Color.CYAN);
		withdrawBtn.setBounds(360, 184, 200, 30);
		
		miniStatementBtn = new JButton("Mini Statement");
		miniStatementBtn.setBackground(Color.CYAN);
		miniStatementBtn.setBounds(40, 256, 200, 30);
		
		balanceEnquiryBtn = new JButton("Balance Enquire");
		balanceEnquiryBtn.setBackground(Color.CYAN);
		balanceEnquiryBtn.setBounds(360, 256, 200, 30);
		
		pinChange = new JButton("Pin Change");
		pinChange.setBackground(Color.CYAN);
		pinChange.setBounds(40, 329, 200, 30);
		
		exit = new JButton("Exit");
		exit.setForeground(Color.white);
		exit.setBackground(Color.red);
		exit.setBounds(360, 329, 200, 30);
		
		balanceEnquiryBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	BalancePage.balancePagePanel(jdbc, atmNumber, frame);
			}});
		
		miniStatementBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Statements.statementPanel(atmNumber, jdbc);
			}});
		
		fastCashBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				//new FastCash(atmNumber, jdbc);
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	FastCash.fashCashPanel(jdbc, atmNumber, frame);
			}});
		
		pinChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	PinChange.pinChangePanel(jdbc, atmNumber, frame, "logged");
			}});
		
		withdrawBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	WithdrawAmount.withdrawAmountPanel(jdbc, atmNumber, frame);
			}});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	WelcomeScreenATM.atmWelcomeScreenPane(jdbc, frame);
			}
			
		});
		
		panel.add(welcome_user);
		panel.add(fastCashBtn);
		panel.add(withdrawBtn);
		panel.add(miniStatementBtn);
		panel.add(balanceEnquiryBtn);
		panel.add(pinChange);
		panel.add(exit);
		
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Aadim Bank");
		lblNewLabel.setBounds(0, 0, 609, 82);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBackground(new Color(238, 238, 238));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBounds(0, 0, 609, 82);
		panel.add(panel_1);

		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
