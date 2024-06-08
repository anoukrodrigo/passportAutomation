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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminPanelClassi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPanelClassi frame = new adminPanelClassi();
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
	public adminPanelClassi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 99));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, -39, 1183, 223);
		contentPane.add(lblNewLabel);
		
		JLabel lblClassified = new JLabel("Classified");
		lblClassified.setForeground(new Color(102, 0, 0));
		lblClassified.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassified.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 99));
		lblClassified.setBounds(10, 135, 1183, 223);
		contentPane.add(lblClassified);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 54));
		passwordField.setBounds(165, 351, 896, 123);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pwd = "hybridWolf@131902";
				String pass = passwordField.getText();
				int psw = passwordField.getText().length();
				if(psw == 0) {
					JOptionPane.showMessageDialog(null, "please fill in the field");
				}else {
					if(pass.equals(pwd)) {
						adminPasswordChange pwdChange = new adminPasswordChange();
						pwdChange.setVisible(true);
						dispose();
						
					}else {
						
					}
				}
				
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(255, 255, 204));
		btnNewButton.setBounds(881, 510, 182, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1_1 = new JButton("back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				adminLogin aLogin = new adminLogin();
				aLogin.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 37));
		btnNewButton_1_1.setBounds(10, 583, 229, 67);
		contentPane.add(btnNewButton_1_1);
	}
}
