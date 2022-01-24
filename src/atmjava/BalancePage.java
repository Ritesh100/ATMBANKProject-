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

public class BalancePage {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void balancePagePanel(JDBCConnection jdbc, String atmNumber, JFrame frame) {
		JPanel panel = new JPanel();
	
		JButton back;
		JLabel welcome_user, balance;
		Font  normal_font  = new Font(Font.SERIF, Font.BOLD,  20);
		Font balance_font = new Font(Font.MONOSPACED, Font.BOLD, 25);

		panel.setLayout(null);
		
		JLabel lblHamroRamroBank = new JLabel("Aadim Bank");
		lblHamroRamroBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblHamroRamroBank.setHorizontalAlignment(SwingConstants.CENTER);
		lblHamroRamroBank.setBounds(0, 0, 586, 73);
		panel.add(lblHamroRamroBank);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBounds(0, 0, 586, 73);
		panel.add(panel_1);
		
		welcome_user = new JLabel("Your balance remaining is", SwingConstants.CENTER);
		welcome_user.setFont(normal_font);
		welcome_user.setBounds(0, 125, 586, 60);
		String balanceOfUser = jdbc.getData("SELECT balance FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		balance = new JLabel(""+balanceOfUser+".00", SwingConstants.CENTER);
	
		balance.setFont(balance_font);
		
		balance.setBounds(101, 186, 400, 60);
		
		back = new JButton("Back");
		
		back.setBounds(256, 258, 100, 30);
		
		//back button on click handler
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	ATMAction.atmActionPanel(jdbc, atmNumber, frame);
			}
			
		});
		
		panel.add(welcome_user);
		panel.add(balance);
		panel.add(back);
		
		frame.getContentPane().add(panel);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
