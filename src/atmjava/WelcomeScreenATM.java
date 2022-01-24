package atmjava;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WelcomeScreenATM {

	/**
	 * @wbp.parser.entryPoint
	 */
	static void atmWelcomeScreenPane(JDBCConnection jdbc, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		JButton nextBtn, exit;
		JLabel enter_atm_no;
		JTextField atmNumberInput;
		
		panel.setLayout(null);
		
		enter_atm_no = new JLabel("Please enter your ATM Card Number",  SwingConstants.CENTER);
		enter_atm_no.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		enter_atm_no.setBounds(0, 127, 586, 30);
		
		atmNumberInput = new JTextField("");
		atmNumberInput.setBounds(150, 172, 300, 30);
		
		nextBtn = new JButton("Next");
		nextBtn.setForeground(Color.BLACK);
		nextBtn.setBackground(Color.CYAN);
		nextBtn.setBounds(250, 230, 100, 30);
		panel.add(enter_atm_no);
		panel.add(atmNumberInput);
		panel.add(nextBtn);
		
		//Make only input numbers in ATM Number Input
		atmNumberInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(atmNumberInput.getText().length()>=6&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isDigit(ch)) {
                	evt.consume();
                }
            }
		});
		
		//Next Button click handler
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(atmNumberInput.getText().length() != 6) {
					atmNumberInput.setText("");
					JOptionPane.showMessageDialog(frame,
						    "Invalid ATM Card number",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
					atmNumberInput.setText("");
					atmNumberInput.requestFocus();
				} else {
					int count = 0;
					try {
						count = jdbc.ifExists("SELECT COUNT(*) FROM atm_users WHERE atm_number = '"+atmNumberInput.getText()+"'");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					if (count == 1) {
						frame.getContentPane().removeAll();
						frame.repaint();
						PinInput.pinInputPanel(jdbc, atmNumberInput.getText().toString(), frame);				
					} else {
						atmNumberInput.setText("");
						JOptionPane.showMessageDialog(frame,
							    "Card Number doesn't exists",
							    "Error",
							    JOptionPane.WARNING_MESSAGE);
						atmNumberInput.requestFocus();
					}
				}
			}});
		
		exit = new JButton("Exit");
		exit.setForeground(Color.BLACK);
		exit.setBackground(Color.red);
		exit.setBounds(250, 272, 100, 30);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jdbc.closeConnection();
				frame.dispose();
				DefaultPage.startActivity();
			}});
		
		panel.add(exit);
		
		frame.getContentPane().add(panel);
		
		JLabel lblHamroRamroBank = new JLabel("Hamro Ramro Bank");
		lblHamroRamroBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblHamroRamroBank.setHorizontalAlignment(SwingConstants.CENTER);
		lblHamroRamroBank.setBounds(0, 0, 586, 73);
		panel.add(lblHamroRamroBank);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 586, 73);
		panel.add(panel_1);
		
		frame.setSize(596, 380);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

