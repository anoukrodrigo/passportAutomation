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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class updatePassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updatePassword frame = new updatePassword();
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
	public updatePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1231, 717);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("nic");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_1.setBounds(10, 234, 177, 49);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField.setColumns(10);
		textField.setBounds(329, 223, 859, 43);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_3 = new JLabel("password");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_3.setBounds(10, 417, 177, 49);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("confirm password");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_4.setBounds(10, 481, 255, 49);
		contentPane.add(lblNewLabel_1_4);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		passwordField.setBounds(337, 422, 851, 43);
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
				 textField_1.setText(strength);
				
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
		passwordField_1.setBounds(337, 486, 851, 43);
		contentPane.add(passwordField_1);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(204, 0, 51));
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		textField_1.setColumns(10);
		textField_1.setBounds(24, 566, 641, 30);
		contentPane.add(textField_1);
		
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
		rdbtnNewRadioButton.setBounds(1062, 554, 130, 42);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String ni = textField.getText();
				String pass = passwordField.getText();
				String conPass = passwordField_1.getText();
				
				int nics = textField.getText().length();
				int pass2 = passwordField.getText().length();
				int conPass2 = passwordField_1.getText().length();
				
				if(nics==0 && pass2==0 && conPass2==0) {
					
					JOptionPane.showMessageDialog(null, "please fill in all fields");
					
				}else {
					
					updatePassNew passUpdater = new updatePassNew();
					boolean success = 	passUpdater.updateUser(ni, pass);
					
					if(success) {
						JOptionPane.showMessageDialog(null, "successfully updated ");
						userLogIn uLogin = new userLogIn();
						uLogin.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "incorrect nic or please enter a different password");
					}
					
				}
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnNewButton.setBounds(13, 627, 252, 43);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				passwordField.setText("");
				passwordField_1.setText("");
				textField_1.setText("");
				textField.setText("");
				
				rdbtnNewRadioButton.setSelected(false);
				
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnReset.setBounds(329, 627, 252, 43);
		contentPane.add(btnReset);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				forgetPassword passFor = new forgetPassword();
				passFor.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(644, 626, 247, 43);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp choose = new chooseFormApp();
				choose.setVisible(true);
				dispose();
				
				
				
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btnCancel.setBounds(920, 627, 252, 43);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(24, -34, 1162, 145);
		contentPane.add(lblNewLabel);
	}

}
