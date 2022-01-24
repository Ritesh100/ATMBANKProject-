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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.util.Base64;
import java.util.Base64.Encoder;

public class PinChange {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void pinChangePanel(JDBCConnection jdbc, String atmNumber, JFrame frame, String status) {
		JPanel panel = new JPanel();
		
		JButton cancel, confirm;
		JLabel enter_pin, enter_pin_again;
		Font  normal_font  = new Font(Font.SERIF, Font.BOLD,  20);
		JPasswordField newPin, confirmPin;
		
		panel.setLayout(null);
		
		JLabel Aadim = new JLabel("Aadim Bank");
		Aadim.setBounds(0, 0, 599, 77);
		panel.add(Aadim);
		Aadim.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		Aadim.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 605, 77);
		panel.add(panel_1);
		
		enter_pin = new JLabel("Enter new PIN", SwingConstants.CENTER);
		enter_pin.setFont(normal_font);
		enter_pin.setBounds(150, 101, 300, 40);
		
		newPin = new JPasswordField("");
		newPin.setFocusable(true);
		newPin.setBounds(150, 142, 300, 40);
		
		enter_pin_again = new JLabel("Confirm PIN", SwingConstants.CENTER);
		enter_pin_again.setFont(normal_font);
		enter_pin_again.setBounds(150, 194, 300, 40);
		
		confirmPin = new JPasswordField("");
		confirmPin.setFocusable(true);
		confirmPin.setBounds(150, 235, 300, 40);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.BLACK);
		cancel.setBackground(Color.red);
		cancel.setBounds(315, 303, 135, 30);
		
		confirm = new JButton("Confirm");
		confirm.setForeground(Color.BLACK);
		confirm.setBackground(Color.CYAN);
		confirm.setBounds(150, 303, 135, 30);
		
		//Pin only numbers with 4 digit setting
		newPin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {		
				if(newPin.getPassword().length >= 4 && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isDigit(ch)) {
                	evt.consume();
                }
            }
		});
		
		//Pin only numbers with 4 digit setting
		confirmPin.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {		
				if(confirmPin.getPassword().length >= 4 && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
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
				if(newPin.getPassword().length == 4 && confirmPin.getPassword().length == 4) {
					String new_pin = new String(newPin.getPassword());
					String confirm_pin = new String(confirmPin.getPassword());
					if(new_pin.equals(confirm_pin)) {
					
						jdbc.executeQuery("UPDATE atm_users SET pin = '"+new_pin+"' WHERE atm_number = '"+atmNumber+"'");
						JOptionPane.showMessageDialog(frame,
							    "PIN changed successfully\nLog In again to continue!!!",
							    "PIN Change",
							    JOptionPane.INFORMATION_MESSAGE);
						frame.getContentPane().removeAll();
			        	frame.repaint();
			        	panel.removeAll();
						WelcomeScreenATM.atmWelcomeScreenPane(jdbc, frame);
					} else {
						JOptionPane.showMessageDialog(frame,
							    "PIN doesn't match",
							    "Error",
							    JOptionPane.WARNING_MESSAGE);
			        	newPin.setText("");
			        	confirmPin.setText("");
					}
				}
			}});
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
	        	frame.repaint();
	        	panel.removeAll();
	        	if(status.equals("logged")) {
	        		ATMAction.atmActionPanel(jdbc, atmNumber, frame);
	        	} else {
	        		PinInput.pinInputPanel(jdbc, atmNumber, frame);
	        	}
			}});
		
		panel.add(enter_pin);
		panel.add(newPin);
		panel.add(enter_pin_again);
		panel.add(confirmPin);
		panel.add(cancel);
		panel.add(confirm);
		
		frame.getContentPane().add(panel);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
