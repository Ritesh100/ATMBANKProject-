package atmjava;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EnterOTP {
	/**
	 * @wbp.parser.entryPoint
	 */
	static void enterOtpScreenPane(String atmNumber, JDBCConnection jdbc, JFrame frame, String otpCode, long mailSentTime) {
		JPanel panel = new JPanel();
		JButton verify, cancel;
		JLabel enter_otp;
		JTextField otpNumberInput;
		Font normal_font = new Font(Font.MONOSPACED, Font.BOLD, 15);
		
		panel.setLayout(null);
		
		JLabel AadimBank = new JLabel("Aadim Bank");
		AadimBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		AadimBank.setHorizontalAlignment(SwingConstants.CENTER);
		AadimBank.setBounds(0, 0, 608, 73);
		panel.add(AadimBank);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBounds(0, 0, 608, 73);
		panel.add(panel_1);
		
		enter_otp = new JLabel("Enter 6 digits code that you got on email:", SwingConstants.CENTER);
		enter_otp.setForeground(Color.BLACK);
		enter_otp.setFont(normal_font);
		enter_otp.setBounds(51, 78, 500, 60);
		
		otpNumberInput = new JTextField("");
		otpNumberInput.setBounds(219, 150, 154, 50);
		
		verify = new JButton("Verify");
		verify.setForeground(Color.BLACK);

		verify.setBounds(250, 230, 100, 30);
		
		cancel = new JButton("Cancel");
		cancel.setForeground(Color.BLACK);
		cancel.setBackground(Color.red);
		cancel.setBounds(250, 272, 100, 30);
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				PinInput.pinInputPanel(jdbc, atmNumber, frame);
			}});
		
		panel.add(enter_otp);
		panel.add(otpNumberInput);
		panel.add(verify);
		panel.add(cancel);
		
		verify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code = otpNumberInput.getText().toString();
				if(code.equals(otpCode)) {
					frame.getContentPane().removeAll();
					frame.repaint();
					long otpEnteredTime = System.currentTimeMillis();
					long minConvert = otpEnteredTime - mailSentTime;
					int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(minConvert);
					if(minutes > 2) {
						JOptionPane.showMessageDialog(frame,
							    "OTP expired",
							    "Error",
							    JOptionPane.WARNING_MESSAGE);
					} else {
						PinChange.pinChangePanel(jdbc, atmNumber, frame, "reset");
					}
				} else {
					JOptionPane.showMessageDialog(frame,
						    "OTP code doesn't match",
						    "Error",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		otpNumberInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(otpNumberInput.getText().length()>=6&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isDigit(ch)) {
                	evt.consume();
                }
            }
		});
		
		
		
		frame.getContentPane().add(panel);
		
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
