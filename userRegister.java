package emigrationDept;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userRegister frame = new userRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1222, 723);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(20, -25, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("full name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1.setBounds(20, 142, 177, 49);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("nic");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_1.setBounds(20, 219, 177, 49);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("E-mail");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_2.setBounds(20, 343, 177, 49);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("password");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_3.setBounds(20, 402, 177, 49);
		contentPane.add(lblNewLabel_1_3);
		
		 
	    
		
		JLabel lblNewLabel_1_4 = new JLabel("confirm password");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_4.setBounds(20, 466, 255, 49);
		contentPane.add(lblNewLabel_1_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField.setBounds(339, 130, 859, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField_1.setColumns(10);
		textField_1.setBounds(339, 208, 859, 43);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField_2.setColumns(10);
		textField_2.setBounds(339, 335, 859, 43);
		contentPane.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		passwordField.setBounds(347, 407, 851, 43);
		contentPane.add(passwordField);
		
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        updatePasswordStrength();
		    }

		    private void updatePasswordStrength() {
				// TODO Auto-generated method stub
		    	 String password = new String(passwordField.getPassword());
				    String strength = getPasswordStrength(password);
				    updateStrengthIndicator(strength);
				
			}

		

		

			private String getPasswordStrength(String password) {
				// TODO Auto-generated method stub
				
				
				
				  int length = password.length();
				    if (length < 6) {
				        return "Weak";
				    } else if (length < 8) {
				        return "Moderate";
				    }
				    
				    // Complexity check
				    boolean hasLowerCase = false;
				    boolean hasUpperCase = false;
				    boolean hasDigit = false;
				    boolean hasSpecialChar = false;
				    
				    for (char ch : password.toCharArray()) {
				        if (Character.isLowerCase(ch)) {
				            hasLowerCase = true;
				        } else if (Character.isUpperCase(ch)) {
				            hasUpperCase = true;
				        } else if (Character.isDigit(ch)) {
				            hasDigit = true;
				        } else if (!Character.isLetterOrDigit(ch)) {
				            hasSpecialChar = true;
				        }
				    }
				    
				    // Check for presence of different character types
				    int complexityScore = (hasLowerCase ? 1 : 0) + 
				                          (hasUpperCase ? 1 : 0) + 
				                          (hasDigit ? 1 : 0) + 
				                          (hasSpecialChar ? 1 : 0);
				    
				    // Assign strength based on complexity
				    if (complexityScore < 2) {
				        return "Weak";
				    } else if (complexityScore < 3) {
				        return "Moderate";
				    } else {
				        return "Strong";
				    }
			  /*  if (password.length() < 6) {
			        return "Weak";
			    } else if (password.length() < 8) {
			        return "Moderate";
			    } else {
			        return "Strong";
			    }*/
			}
			
			private void updateStrengthIndicator(String strength) {
				// TODO Auto-generated method stub
				 textField_3.setText(strength);
				
			}
			

			@Override
		    public void removeUpdate(DocumentEvent e) {
		        updatePasswordStrength();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        updatePasswordStrength();
		    }
		});

		
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		passwordField_1.setBounds(347, 471, 851, 43);
		contentPane.add(passwordField_1);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(204, 0, 51));
		textField_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		textField_3.setBounds(34, 551, 641, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Show");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (rdbtnNewRadioButton.isSelected()) {
	                    passwordField.setEchoChar((char) 0);
	                    passwordField_1.setEchoChar((char) 0);
	                } else {
	                    passwordField.setEchoChar('\u25cf');
	                    passwordField_1.setEchoChar('\u25cf');
	                    
	                }
				
				
				
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton.setBounds(1072, 539, 130, 42);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				String fullname = textField.getText();
				String nic = textField_1.getText();
				
				String username = textField_4.getText();
				String email = textField_2.getText();
				String password=passwordField.getText();
				String conPwd = passwordField_1.getText();
				
				int f = textField.getText().length();
				int n = textField_1.getText().length();
				int u = textField_4.getText().length();
				int em = textField_2.getText().length();
				int pass = passwordField.getText().length();
				
				if(f==0 && n==0 && u==0 && em==0 && pass==0 ) {
					JOptionPane.showMessageDialog(null, "please fill in all fields!");
				}
				else {
					
					if(password.equals(conPwd)) {
				
			    boolean registrationStatus = passwordHash.registerUser(fullname,nic,username,email,password);

		        if (registrationStatus) {
		           JOptionPane.showMessageDialog(null, "you have been successfully registered");
		           
		           userLogIn login = new userLogIn();
		           login.setVisible(true);
		           dispose();
		           
		        } else {
		        	 JOptionPane.showMessageDialog(null, "registration failed or you already hav an account, please login");
		        }
					}
					else {
						 JOptionPane.showMessageDialog(null, "passwords don't match");
					}
				}
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnNewButton.setBounds(23, 612, 252, 43);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				// Assuming radioButton is your JRadioButton object
				rdbtnNewRadioButton.setSelected(false);

				
				
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnReset.setBounds(339, 612, 252, 43);
		contentPane.add(btnReset);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp chooseF = new chooseFormApp();
				chooseF.setVisible(true);
				dispose();
				
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnCancel.setBounds(930, 612, 252, 43);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("username");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_2_1.setBounds(20, 278, 177, 49);
		contentPane.add(lblNewLabel_1_2_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField_4.setColumns(10);
		textField_4.setBounds(339, 270, 859, 43);
		contentPane.add(textField_4);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				userLogIn uLogin = new userLogIn();
				uLogin.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(654, 611, 247, 43);
		contentPane.add(btnNewButton_1_1);
	}
}
