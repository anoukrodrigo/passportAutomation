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

public class adminPasswordChange extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPasswordChange frame = new adminPasswordChange();
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
	public adminPasswordChange() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 712);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 99));
		lblNewLabel.setBounds(10, -59, 1183, 223);
		contentPane.add(lblNewLabel);
		
		JLabel lblClassified = new JLabel("Classified");
		lblClassified.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassified.setForeground(new Color(102, 0, 0));
		lblClassified.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 99));
		lblClassified.setBounds(-18, 52, 1183, 223);
		contentPane.add(lblClassified);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(32, 233, 238, 55);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(18, 357, 238, 55);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField.setBounds(316, 346, 849, 67);
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
			    }  */
			}
			
			private void updateStrengthIndicator(String strength) {
				// TODO Auto-generated method stub
				textField.setText(strength);
				
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

		
		
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Show");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 if (rdbtnNewRadioButton.isSelected()) {
	                    passwordField.setEchoChar((char) 0);
	                    passwordField_2.setEchoChar((char) 0);
	                } else {
	                    passwordField.setEchoChar('\u25cf');
	                    passwordField_2.setEchoChar('\u25cf');
	                    
	                }
				
				
				
				
				
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		rdbtnNewRadioButton.setBounds(1063, 536, 103, 38);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton_2 = new JButton("cancel");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_2.setBounds(936, 598, 229, 67);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				passwordField_1.setText("");
				passwordField.setText("");
				passwordField_2.setText("");
				rdbtnNewRadioButton.setSelected(false);
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1.setBounds(323, 598, 229, 67);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = passwordField_1.getText();
				String password = passwordField.getText();
				String conPwd =  passwordField_2.getText();
				
				int user = passwordField_1.getText().length();
				int pass = passwordField.getText().length();
				int conPass = passwordField_2.getText().length();
				
				if(user == 0 && pass == 0 && conPass == 0) {
					
					JOptionPane.showMessageDialog(null, "please fill in all fields.");
					
				}
				else {
					if(password.equals(conPwd)) {
				
				adminHashandStore regisadmin = new adminHashandStore ();
			    boolean register = regisadmin.registerAdmin(username, password);
			
			if(register) {
				
				JOptionPane.showMessageDialog(null, "registration  successfull!");
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "unsuccessful registration!");
				
			}}else {
				JOptionPane.showMessageDialog(null, "passwords don't match");
			}
			
			
				}	
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton.setBounds(18, 598, 229, 67);
		contentPane.add(btnNewButton);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField_1.setBounds(316, 233, 849, 67);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField_2.setBounds(316, 447, 849, 67);
		contentPane.add(passwordField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("confirm password ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1_1.setBounds(18, 459, 238, 55);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(316, 536, 676, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				adminLogin aLogin = new adminLogin();
				aLogin.setVisible(true);
				dispose();
				
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(641, 598, 229, 67);
		contentPane.add(btnNewButton_1_1);
	}
}
