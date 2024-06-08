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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminLogin extends JFrame {

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
					adminLogin frame = new adminLogin();
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
	public adminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1221, 721);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(35, -41, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(49, 169, 238, 55);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(35, 293, 238, 55);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField.setColumns(10);
		textField.setBounds(333, 157, 849, 67);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField.setBounds(333, 289, 849, 67);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = passwordField.getText();
				
				int u = textField.getText().length();
				int p = passwordField.getText().length();
				int iterations = 10000;
				
			if(u==0  && p==0) {
				JOptionPane.showMessageDialog(null, " please fill all the fields");
			}
			else {
				
				adminHashandStore hashAdmin = new adminHashandStore();
			boolean hashStatus = 	hashAdmin.verifyadmin(username, password,iterations);
			
			if(hashStatus) {
				
				JOptionPane.showMessageDialog(null, " login successful!");
				adminViewDetails adminView = new adminViewDetails();
				adminView.setVisible(true);
				dispose();
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "username or password incorrect");
				
			}
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
		btnNewButton.setBounds(35, 534, 229, 67);
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
		btnNewButton_1.setBounds(351, 534, 229, 67);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseFormApp chooseForm = new chooseFormApp();
				chooseForm.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_2.setBounds(953, 534, 229, 67);
		contentPane.add(btnNewButton_2);
		
		
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		rdbtnNewRadioButton.setBounds(1079, 372, 103, 38);
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanelClassi adminPanel = new adminPanelClassi();
				adminPanel.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBackground(new Color(204, 255, 51));
		btnNewButton_3.setForeground(new Color(204, 255, 51));
		btnNewButton_3.setBounds(333, 385, 120, 38);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				chooseFormApp choose = new chooseFormApp();
				choose.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(659, 534, 229, 67);
		contentPane.add(btnNewButton_1_1);
	}

}
