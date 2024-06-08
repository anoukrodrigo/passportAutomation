package emigrationDept;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class userLogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userLogIn frame = new userLogIn();
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
	public userLogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1196, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, -41, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(24, 220, 238, 55);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(10, 344, 238, 55);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField.setBounds(308, 208, 849, 67);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField.setBounds(308, 340, 849, 67);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = passwordField.getText();
				int iterations = 10000;
				// Call the verifyUser method
				boolean verificationStatus = passwordHash.verifyUser(username, password,iterations );

				// Check the verification status
				if (verificationStatus) {
				    JOptionPane.showMessageDialog(null, "Login successful");
				    userApplication uApp = new userApplication();
				    uApp.setVisible(true);
				    dispose();
				    
				    
				    
				} else {
				    JOptionPane.showMessageDialog(null, "Username or password incorrect");
				}
			
				
				
			}
		});
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Show");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if (rdbtnNewRadioButton.isSelected()) {
	                    passwordField.setEchoChar((char) 0);
	                   
	                } else {
	                    passwordField.setEchoChar('\u25cf');
	                    
	                    
	                }
				
				
				
			}
		});
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton.setBounds(10, 585, 229, 67);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				passwordField.setText("");
				rdbtnNewRadioButton.setSelected(false);
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1.setBounds(331, 585, 229, 67);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp choose = new chooseFormApp();
				choose.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_2.setBounds(928, 585, 229, 67);
		contentPane.add(btnNewButton_2);
	
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		rdbtnNewRadioButton.setBounds(1054, 413, 103, 38);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_2 = new JLabel("don't have account? ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(24, 450, 215, 19);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("Sign up");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userRegister register = new userRegister();
				register.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3.setBounds(240, 451, 131, 19);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("View Status? ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2_1.setBounds(572, 490, 131, 19);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton_3_1 = new JButton("view");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userApplicationStatus appStatus = new userApplicationStatus();
				appStatus.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3_1.setBounds(701, 491, 131, 19);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp choose = new chooseFormApp();
				choose.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(649, 585, 229, 67);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("forgot password?");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2_2.setBounds(24, 510, 215, 19);
		contentPane.add(lblNewLabel_2_2);
		
		JButton btnNewButton_3_2 = new JButton("change");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				forgetPassword passForgot = new forgetPassword();
				passForgot.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3_2.setBounds(189, 511, 131, 19);
		contentPane.add(btnNewButton_3_2);
	}
}
