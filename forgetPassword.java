package emigrationDept;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.Color;

public class forgetPassword extends JFrame {

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
					forgetPassword frame = new forgetPassword();
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
	public forgetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1226, 725);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(24, 246, 238, 55);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		textField.setColumns(10);
		textField.setBounds(308, 234, 849, 67);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("nic");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(10, 370, 238, 55);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 39));
		passwordField.setBounds(308, 366, 849, 67);
		contentPane.add(passwordField);
		
		JButton btnVerify = new JButton("verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				String nic = passwordField.getText();
				
				forgetPassNew forget = new forgetPassNew();
				  boolean verified = forget.verifyNICAndUsername(nic, user);
				
				if(verified) {
					JOptionPane.showMessageDialog(null, "successfully identified ");
					updatePassword upPass = new updatePassword();
					upPass.setVisible(true);
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "incorrect nic or username");
				}
				
				
				
			}
		});
		btnVerify.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnVerify.setBounds(10, 611, 229, 67);
		contentPane.add(btnVerify);
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
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userLogIn uLogin = new userLogIn();
				uLogin.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(649, 611, 229, 67);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				passwordField.setText("");
				rdbtnNewRadioButton.setSelected(false);
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1.setBounds(331, 611, 229, 67);
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
		btnNewButton_2.setBounds(928, 611, 229, 67);
		contentPane.add(btnNewButton_2);
		
		
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		rdbtnNewRadioButton.setBounds(1054, 439, 103, 38);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(24, -26, 1162, 145);
		contentPane.add(lblNewLabel);
	}
}
