package atmjava;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.toedter.calendar.JDateChooser;


public class OpenBankAccount {
	static String isFile = "no";

	/**
	 * @wbp.parser.entryPoint
	 */
	static void openBankAccountPanel(JDBCConnection jdbc, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		JLabel first_name, last_name, person_title, gender, address, maritial_status, date_of_birth, email_address, password;
		JTextField first_name_input, last_name_input, email_input, address_input;
		JPasswordField pass;
		JComboBox<?> prefixSelect;
		JButton cancel, next;
		
		JButton addImage = new JButton("Add Image");
		addImage.setBounds(343, 270, 168, 30);
		
		JLabel photoLabel = new JLabel();
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		photoLabel.setBounds(343,133, 168, 125);
		photoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		addImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					if(chooser.getDate() == null) {
//						System.out.println("null");
//					}

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.addChoosableFileFilter(new ImageFilter());

		            fileChooser.setAcceptAllFileFilterUsed(false);

		            int option = fileChooser.showOpenDialog(frame);
		            if(option == JFileChooser.APPROVE_OPTION){
		        		

		               File file = fileChooser.getSelectedFile();
		              
		               final ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(200, 300, Image.SCALE_SMOOTH));
		               photoLabel.setIcon(icon);
		               isFile = file.getPath();
		            }
		           
				} catch(Exception ep) {
					ep.printStackTrace();
				}
			}
			
		});
		
		JRadioButton male_btn=new JRadioButton("Male");    
		male_btn.setBackground(Color.LIGHT_GRAY);
		JRadioButton female_btn=new JRadioButton("Female"); 
		female_btn.setBackground(Color.LIGHT_GRAY);
		
		JRadioButton unmarried_btn = new JRadioButton("Single");
		unmarried_btn.setBackground(Color.LIGHT_GRAY);
		JRadioButton married_btn = new JRadioButton("Married");
		married_btn.setBackground(Color.LIGHT_GRAY);
		
		ButtonGroup gender_btn=new ButtonGroup();
		ButtonGroup married_status_btn = new ButtonGroup();
		
		
		
		gender_btn.add(female_btn);
		gender_btn.add(male_btn);
		
		married_status_btn.add(unmarried_btn);
		married_status_btn.add(married_btn);
		
		Font title_font = new Font(Font.SERIF, Font.BOLD, 20);
		Font label_font = new Font(Font.SERIF, Font.PLAIN, 15);
		
		String[] prefix = { "Mr.", "Mrs.", "Miss"};
		
		panel.setLayout(null);
        
        person_title = new JLabel("Title");
        person_title.setFont(label_font);
        person_title.setBounds(40, 314, 100, 30);
        
        prefixSelect = new JComboBox(prefix);
        prefixSelect.setBounds(107, 314, 100, 30);
        
        first_name = new JLabel("First Name");
        first_name.setFont(label_font);
        first_name.setBounds(240, 319, 100, 20);
        
        first_name_input = new JTextField("");
        first_name_input.setBounds(343, 315, 181, 30);
        
        last_name = new JLabel("Last Name");
        last_name.setFont(label_font);
        last_name.setBounds(549, 319, 100, 20);
        
        last_name_input = new JTextField("");
        last_name_input.setBounds(637, 315, 190, 30);
        
        gender = new JLabel("Gender");
        gender.setFont(label_font);
        gender.setBounds(485, 371, 100, 30);
        
        maritial_status = new JLabel("Maritial Status");
        maritial_status.setFont(label_font);
        maritial_status.setBounds(40, 371, 133, 30);
        
        male_btn.setBounds(610, 371, 100, 30);
        female_btn.setBounds(727, 371, 100, 30);
        
        unmarried_btn.setBounds(185, 371, 100, 30);
        married_btn.setBounds(289, 371, 100, 30);
        
        date_of_birth = new JLabel("Date of birth");
        date_of_birth.setFont(label_font);
        date_of_birth.setBounds(485, 431, 140, 30);
        
      //DatePicker datePicker;
  		JDateChooser chooser = new JDateChooser();
  		chooser.setLocale(Locale.US);
  		Date date = new Date();
  		//chooser.setMaxSelectableDate(date);
  		chooser.setBounds(627, 431, 200, 30);
  		
  		address = new JLabel("Address");
  		address.setFont(label_font);
  		address.setBounds(40, 431, 100, 30);
  		
  		address_input = new JTextField("");
  		address_input.setBounds(140, 432, 200, 30);
  		
  		email_address = new JLabel("Email");
  		email_address.setFont(label_font);
  		email_address.setBounds(40, 490, 100, 30);
  		
  		email_input = new JTextField("");
        email_input.setBounds(140, 491, 200, 30);
        
        password = new JLabel("Password");
  		password.setFont(label_font);
        password.setBounds(485, 490, 100, 30);
        
        pass = new JPasswordField("");
        pass.setBounds(627, 491, 200, 30);
        
        cancel = new JButton("Cancel");
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.red);
        cancel.setBounds(386, 575, 100, 30);
        
        next = new JButton("Next");
        next.setForeground(Color.BLACK);
        next.setBackground(Color.CYAN);
        next.setBounds(386, 533, 100, 30);
        
        first_name_input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(first_name_input.getText().length()>=20&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isAlphabetic(ch)) {
                	evt.consume();
                }
            }
		});
        
        address_input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(address_input.getText().length()>=40&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isLetterOrDigit(ch)) {
                	evt.consume();
                }
            }
		});
        
        email_input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(email_input.getText().length()>=300&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
            }
		});
        
        
        pass.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(pass.getPassword().length>=15&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
				char ch = evt.getKeyChar();

                if(Character.isWhitespace(ch)) {
                	evt.consume();
                }
            }
		});
        
        
        last_name_input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if(last_name_input.getText().length()>=20&&!(evt.getKeyChar()==KeyEvent.VK_DELETE||evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
		            evt.consume();
		         }
                char ch = evt.getKeyChar();

                if(!Character.isAlphabetic(ch)) {
                	evt.consume();
                }
            }
		});
        
        cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jdbc.closeConnection();
				frame.dispose();
				DefaultPage.startActivity();
			}});
        
        next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String prefix = prefixSelect.getSelectedItem().toString();
				String firstName = first_name_input.getText().toString().trim();
				String lastName = last_name_input.getText().toString().trim();
				String gender = male_btn.isSelected() ? "Male" : female_btn.isSelected() ? "Female" : "null";
				String marriedStatus = unmarried_btn.isSelected() ? "Unmarried" : married_btn.isSelected() ? "Married" : "null";
				String date = "null";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
				 if(chooser.getDate() != null) {
					 date = sdf.format(chooser.getDate());
				 }
				 String address = address_input.getText().toString().trim();
				 String email = email_input.getText().toString().toLowerCase().trim();
				 String passwordInput = new String(pass.getPassword());
				 passwordInput = passwordInput.trim();
				 if(firstName.isEmpty() || lastName.isEmpty() || gender.equals("null") || marriedStatus.equals("null") || date.equals("null") || address.isEmpty() || email.isEmpty() || passwordInput.isEmpty()) {
					 showError("Please fill in all the fields");
				 } else {
					if(isFile.equals("no")) {
						showError("Photo not selected");
					} else {
						 Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
						 if(ptr.matcher(email).matches()) {
							 int getIntEmail = jdbc.getDataInt("SELECT COUNT(*) FROM user WHERE email='"+email+"'");
							 if(getIntEmail == 1) {
								 showError("Email Address already in use");
							 } else {
								Random rand = new Random();
								String defCode;
								boolean isUnique = false;
								do {
									defCode = "D";
									for (int i = 0; i < 9; i++)
								    {
								        int n = rand.nextInt(10) + 0;
								        defCode += Integer.toString(n);
								    }
									isUnique = ifExistsCode(defCode, jdbc);
									} while(isUnique == false);
									try {
										jdbc.preparedQuery(defCode, prefix, firstName, lastName, address, email, date, gender, marriedStatus, passwordInput, isFile, 0);
										JOptionPane.showMessageDialog(null,
											    "Account Created Successfully with account number\n"+defCode+"",
											    "Success",
											    JOptionPane.INFORMATION_MESSAGE);
												jdbc.closeConnection();
												frame.dispose();
												DefaultPage.startActivity();
									} catch (Exception epp) {
										JOptionPane.showMessageDialog(null,
											    "Error creating account",
											    "Error",
											    JOptionPane.ERROR_MESSAGE);
									}
							 }
						 } else {
							 showError("Email Address not valid");
							 email_input.setText("");
						 }
					}
				 }
				
			}});
		panel.add(person_title);
		panel.add(prefixSelect);
		panel.add(first_name);
		panel.add(first_name_input);
		panel.add(last_name);
		panel.add(last_name_input);
		panel.add(gender);
		panel.add(male_btn);
		panel.add(female_btn);
		panel.add(maritial_status);
		panel.add(unmarried_btn);
		panel.add(married_btn);
		panel.add(photoLabel);
		panel.add(addImage);
		panel.add(date_of_birth);
  		panel.add(chooser);
  		panel.add(address);
  		panel.add(address_input);
  		panel.add(email_address);
  		panel.add(email_input);
  		panel.add(password);
  		panel.add(pass);
  		panel.add(cancel);
  		panel.add(next);
				
		frame.getContentPane().add(panel);
		
		JLabel lblHamroRamroBank = new JLabel("Hamro Ramro Bank");
		lblHamroRamroBank.setBounds(0, 0, 854, 77);
		panel.add(lblHamroRamroBank);
		lblHamroRamroBank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblHamroRamroBank.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(0, 0, 856, 77);
		panel.add(panel_1);
		
		JLabel lblBankAccountOpening = new JLabel("Bank Account Opening Form");
		lblBankAccountOpening.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblBankAccountOpening.setHorizontalAlignment(SwingConstants.CENTER);
		lblBankAccountOpening.setBounds(0, 74, 854, 47);
		panel.add(lblBankAccountOpening);
		frame.setSize(800, 650);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static void showError(String message) {
		JOptionPane.showMessageDialog(null,
			    message,
			    "Error",
			    JOptionPane.WARNING_MESSAGE);		
	}
	static boolean ifExistsCode(String code, JDBCConnection jdbc) {
		boolean status;
		int count = jdbc.getDataInt("SELECT COUNT(*) FROM user WHERE account_number = '"+code+"'");
		if(count == 0) {
			status = true;
		} else {
			status = false;
		}
		return status;
	}
}

class ImageFilter extends FileFilter {
	   public final static String JPEG = "jpeg";
	   public final static String JPG = "jpg";
	   public final static String GIF = "gif";
	   public final static String TIFF = "tiff";
	   public final static String TIF = "tif";
	   public final static String PNG = "png";
	   
	   @Override
	   public boolean accept(File f) {
	      if (f.isDirectory()) {
	         return true;
	      }

	      String extension = getExtension(f);
	      if (extension != null) {
	         if (extension.equals(TIFF) ||
	            extension.equals(TIF) ||
	            extension.equals(GIF) ||
	            extension.equals(JPEG) ||
	            extension.equals(JPG) ||
	            extension.equals(PNG)) {
	            return true;
	         } else {
	            return false;
	         }
	      }
	      return false;
	   }

	   @Override
	   public String getDescription() {
	      return "Image Only";
	   }

	   String getExtension(File f) {
	      String ext = null;
	      String s = f.getName();
	      int i = s.lastIndexOf('.');
	   
	      if (i > 0 &&  i < s.length() - 1) {
	         ext = s.substring(i+1).toLowerCase();
	      }
	      return ext;
	   } 
	}
