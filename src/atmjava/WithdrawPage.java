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

public class WithdrawPage {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	static void withdrawPagePanel(JDBCConnection jdbc, String atmNumber, int withdrawedBalance, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		JButton continueBtn;
		JLabel welcome_user, balance;
		Font  normal_font  = new Font(Font.SERIF, Font.BOLD,  20);
		Font balance_font = new Font(Font.MONOSPACED, Font.BOLD, 15);

		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hamro Ramro Bank");
		lblNewLabel.setBounds(0, 0, 609, 82);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBackground(new Color(238, 238, 238));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 609, 82);
		panel.add(panel_1);
		
		welcome_user = new JLabel("Successfully withrawed, RS. "+withdrawedBalance+"", SwingConstants.CENTER);
		welcome_user.setFont(normal_font);
		welcome_user.setBounds(100, 150, 400, 35);
		
		String balanceOfUser = jdbc.getData("SELECT balance FROM user WHERE user_id = (SELECT user_id FROM atm_users WHERE atm_number = '"+atmNumber+"')");
		balance = new JLabel("Remaining Balance: "+balanceOfUser+".00", SwingConstants.CENTER);
		balance.setFont(balance_font);
		balance.setForeground(Color.CYAN);
		balance.setBounds(0, 200, 584, 60);
		
		continueBtn = new JButton("Continue");
		continueBtn.setForeground(Color.BLACK);
		continueBtn.setBackground(Color.CYAN);
		continueBtn.setBounds(460, 300, 100, 30);
		
		//continue button on click handler
		continueBtn.addActionListener(new ActionListener() {

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
		panel.add(continueBtn);
		
		frame.getContentPane().add(panel);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

