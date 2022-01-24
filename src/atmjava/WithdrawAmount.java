package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WithdrawAmount {
	/**
	 * @wbp.parser.entryPoint
	 */
	static int getBalanceFromDB(String atmNumber, JDBCConnection jdbc) {
		int balance = jdbc.getDataInt("SELECT balance FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		return balance;
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	static void withdrawAmountPanel(JDBCConnection jdbc, String atmNumber, JFrame frame) {
		JPanel panel = new JPanel();
		JButton cancel, confirm;
		JLabel enter_withdraw_amount, warning_text;
		Font  normal_font  = new Font(Font.SERIF, Font.BOLD,  20);
		Font  warning_font  = new Font(Font.SERIF, Font.ITALIC,  15);
		JTextField withdrawAmount;
		
		panel.setLayout(null);
		
		enter_withdraw_amount = new JLabel("Enter amount to be withdrawed", SwingConstants.CENTER);
		enter_withdraw_amount.setFont(normal_font);
		enter_withdraw_amount.setBounds(150, 80, 300, 40);
		
		withdrawAmount = new JTextField("");
		withdrawAmount.setFocusable(true);
		withdrawAmount.setBounds(150, 150, 300, 40);
		
		warning_text = new JLabel("Note: Only the multiples of 500 can be withdrawed. eg: 1500, 3500, 500", SwingConstants.CENTER);
		warning_text.setForeground(Color.red);
		warning_text.setFont(warning_font);
		warning_text.setBounds(50, 220, 500, 40 );
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.white);
		cancel.setBackground(Color.red);
		cancel.setBounds(40, 300, 100, 30);
		
		confirm = new JButton("Withdraw");
		confirm.setForeground(Color.white);
		confirm.setBackground(Color.green);
		confirm.setBounds(460, 300, 100, 30);
		
		withdrawAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {	
				if(withdrawAmount.getText().length()>=6&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isDigit(ch)) {
                	evt.consume();
                }
            }
		});
		
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String enteredAmount = withdrawAmount.getText();
				int amount = 0;
				try {
					amount = Integer.parseInt(enteredAmount);
				} catch (Exception execp) {
					execp.printStackTrace();
				}
				if(amount % 500 == 0 && amount != 0) {
					int getBalance = getBalanceFromDB(atmNumber, jdbc);
					if(getBalance >= amount) {
						if(amount > 25000) {
							JOptionPane.showMessageDialog(frame,
								    "Only a transaction of amount less than\nor equal to 25000 can be done at once",
								    "Limit Cross",
								    JOptionPane.WARNING_MESSAGE);
							withdrawAmount.setText("");
						} else {
							performTrans(atmNumber, jdbc, amount,frame, panel);
						}
					} else {
						JOptionPane.showMessageDialog(frame,
							    "Insufficient Balance",
							    "Error",
							    JOptionPane.WARNING_MESSAGE);
						withdrawAmount.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Either withdraw amount is not a multiple of 500\nor there is an error doing transaction",
						    "Withdraw Error",
						    JOptionPane.ERROR_MESSAGE);
					withdrawAmount.setText("");
				}
			}});
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	ATMAction.atmActionPanel(jdbc, atmNumber, frame);
			}
			
		});
		
		panel.add(enter_withdraw_amount);
		panel.add(withdrawAmount);
		panel.add(warning_text);
		panel.add(cancel);
		panel.add(confirm);
		
		frame.getContentPane().add(panel);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
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
	
	/**
	 * @wbp.parser.entryPoint
	 */
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
