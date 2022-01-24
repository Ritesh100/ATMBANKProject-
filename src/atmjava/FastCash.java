package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FastCash {
	
	static int getBalanceFromDB(String atmNumber, JDBCConnection jdbc) {
		int balance = jdbc.getDataInt("SELECT balance FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		return balance;
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	static void fashCashPanel(JDBCConnection jdbc, String atmNumber, JFrame frame) {
		//Definitions
		JPanel panel = new JPanel();
		JButton exit, five00Btn, one000Btn, two000Btn, three000Btn, five000Btn, ten000Btn, fifteen000Btn, twenty5000Btn;
		JLabel welcome_user;
		Font  normal_font  = new Font(Font.SERIF, Font.BOLD,  20);
		
		panel.setLayout(null);
		
		JLabel lblHamroRamroBank = new JLabel("Hamro Ramro Bank");
		lblHamroRamroBank.setBounds(0, 0, 605, 77);
		panel.add(lblHamroRamroBank);
		lblHamroRamroBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblHamroRamroBank.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 577, 77);
		panel.add(panel_1);
		
		welcome_user = new JLabel("Select amount:", SwingConstants.CENTER);
		welcome_user.setFont(normal_font);
		welcome_user.setBounds(150, 10, 300, 30);
		
		five00Btn = new JButton("Rs. 500");
		five00Btn.setBounds(40, 164, 111, 30);
		
		one000Btn = new JButton("Rs. 1,000");
		one000Btn.setBounds(40, 206, 111, 30);
		
		two000Btn = new JButton("Rs. 2,000");
		two000Btn.setBounds(40, 248, 111, 30);
		
		three000Btn = new JButton("Rs. 3,000");
		three000Btn.setBounds(40, 290, 111, 30);
		
		five000Btn = new JButton("Rs. 5,000");
		five000Btn.setBounds(427, 164, 111, 30);
		
		ten000Btn = new JButton("Rs. 10,000");
		ten000Btn.setBounds(427, 206, 111, 30);
		
		fifteen000Btn = new JButton("Rs. 15,000");
		fifteen000Btn.setBounds(427, 248, 111, 30);
		
		twenty5000Btn = new JButton("Rs. 25,000");
		twenty5000Btn.setBounds(427, 290, 111, 30);
		
		exit = new JButton("Back");
		exit.setForeground(Color.white);
		exit.setBackground(Color.red);
		exit.setBounds(241, 342, 100, 30);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	ATMAction.atmActionPanel(jdbc, atmNumber, frame);
			}});
		
		five00Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 500) {
					performTrans(atmNumber, jdbc, 500, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		one000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 1000) {
					performTrans(atmNumber, jdbc, 1000, frame, panel);

				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		two000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 2000) {
					performTrans(atmNumber, jdbc, 2000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		three000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 3000) {
					performTrans(atmNumber, jdbc, 3000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		five000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 5000) {
					performTrans(atmNumber, jdbc, 5000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		ten000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 10000) {
					performTrans(atmNumber, jdbc, 10000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		fifteen000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 15000) {
					performTrans(atmNumber, jdbc, 15000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		twenty5000Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getBalance = getBalanceFromDB(atmNumber, jdbc);
				if(getBalance >= 25000) {
					performTrans(atmNumber, jdbc, 25000, frame, panel);
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Insufficient Balance",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}});
		
		
		panel.add(welcome_user);
		panel.add(five00Btn);
		panel.add(one000Btn);
		panel.add(two000Btn);
		panel.add(three000Btn);
		panel.add(five000Btn);
		panel.add(ten000Btn);
		panel.add(fifteen000Btn);
		panel.add(twenty5000Btn);
		panel.add(exit);
				
		frame.getContentPane().add(panel);
		
		JLabel lblSelectAmount = new JLabel("Select Amount");
		lblSelectAmount.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblSelectAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAmount.setBounds(0, 89, 579, 41);
		panel.add(lblSelectAmount);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static void performTrans(String atmNumber, JDBCConnection jdbc, int amount, JFrame frame, JPanel panel) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now).toString();
		
		Random rand = new Random();
		String defCode;
		boolean isUnique = false;
		do {
			defCode = "AB";
			for (int i = 0; i < 8; i++)
		    {
		        int n = rand.nextInt(10) + 0;
		        defCode += Integer.toString(n);
		    }
			isUnique = ifExistsCode(defCode, jdbc);
		} while(isUnique == false);
		
		int u_id = jdbc.getDataInt("SELECT user_id FROM atm_users WHERE atm_number='"+atmNumber+"'");
		jdbc.executeQuery("INSERT INTO transactions(transaction_id, user_id, debit_credit, date_performed ,amount, remarks) VALUES('"+defCode+"', '"+u_id+"', 'debit', '"+date+"' , '"+amount+"', 'ATM Withdrawl')");
		jdbc.executeQuery("UPDATE user SET balance = balance - '"+amount+"' WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		frame.getContentPane().removeAll();
    	frame.repaint();
    	panel.removeAll();
    	WithdrawPage.withdrawPagePanel(jdbc, atmNumber, amount, frame);
	}
	
	static boolean ifExistsCode(String code, JDBCConnection jdbc) {
		boolean status;
		int count = jdbc.getDataInt("SELECT COUNT(*) FROM transactions WHERE transaction_id = '"+code+"'");
		if(count == 0) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}
}


