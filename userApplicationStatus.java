package emigrationDept;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class userApplicationStatus extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userApplicationStatus frame = new userApplicationStatus();
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
	public userApplicationStatus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1226, 722);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(26, -27, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N.I.C");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 56));
		lblNewLabel_1.setBounds(36, 134, 203, 82);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 43));
		lblNewLabel_1_1.setBounds(26, 243, 203, 82);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("status");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 41));
		lblNewLabel_1_2.setBounds(26, 406, 203, 82);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("full name");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 39));
		lblNewLabel_1_3.setBounds(26, 498, 203, 82);
		contentPane.add(lblNewLabel_1_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		passwordField.setBounds(249, 134, 933, 68);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		passwordField_1.setBounds(249, 243, 933, 68);
		contentPane.add(passwordField_1);
		
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
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		rdbtnNewRadioButton.setBounds(1079, 317, 103, 38);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nic = passwordField.getText();
				String password = passwordField_1.getText();

				UserDAO getInfor = new UserDAO();
				
				  String[] userInfo = getInfor.getUserInfo(nic, password);
				  
				  if (userInfo != null) {
			            String applicationStatus = userInfo[0];
			            String fullName = userInfo[1];
			            System.out.println("Application Status: " + applicationStatus);
			            System.out.println("Full Name: " + fullName);
			            
			            getHashedPass getPass = new getHashedPass();
		                getPass.setApplicationStatus(applicationStatus);
		                getPass.setFullName(fullName);
		                
		                textField.setText(getPass.getApplicationStatus());
		                textField_1.setText(getPass.getFullName());
			            
			        } else {
			            System.out.println("User not found or invalid credentials.");
			        }
			
			
				
				
			}
		});
		
		
		
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnNewButton.setBounds(26, 606, 274, 69);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				passwordField.setText("");
				passwordField_1.setText("");
				textField.setText("");
				textField_1.setText("");
				
				rdbtnNewRadioButton.setSelected(false);
				
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnReset.setBounds(341, 606, 274, 69);
		contentPane.add(btnReset);
		
		JButton btnCacel = new JButton("cancel");
		btnCacel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp chooseForm = new chooseFormApp();
				chooseForm.setVisible(true);
				dispose();
				
				
			}
		});
		btnCacel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnCacel.setBounds(914, 606, 274, 69);
		contentPane.add(btnCacel);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		textField.setBounds(249, 403, 933, 68);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		textField_1.setColumns(10);
		textField_1.setBounds(255, 498, 933, 68);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userLogIn uLogin = new userLogIn();
				uLogin.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(654, 606, 229, 67);
		contentPane.add(btnNewButton_1_1);
	}

}
