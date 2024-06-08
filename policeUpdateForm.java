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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class policeUpdateForm extends JFrame {

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
					policeUpdateForm frame = new policeUpdateForm();
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
	public policeUpdateForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 724);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(10, -29, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Police Status Panel");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 58));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(83, 66, 1048, 167);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		textField.setBounds(374, 262, 757, 109);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 38));
		passwordField.setBounds(374, 400, 757, 109);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    int ni = passwordField.getText().length();
			    int pol = textField.getText().length();
				String nic = passwordField.getText();
				String policeUpdate = textField.getText();
				
				
				if(ni==0 && pol==0) {
					  JOptionPane.showMessageDialog(null, "please fill in all fields");
				}
				
				else {
				PoliceUpdater policeUpdater = new PoliceUpdater();

				// Call the updatePoliceStatus method
				boolean updateSuccess = policeUpdater.updatePoliceStatus(nic, policeUpdate);

				// Check if the update was successful
				if (updateSuccess) {
				    JOptionPane.showMessageDialog(null, "Police status updated successfully!");
				    JOptionPane.showMessageDialog(null, "thank you for submitting!");
				    
				    chooseFormApp chooseApp = new chooseFormApp();
				    chooseApp.setVisible(true);
				    dispose();
				    
				} else {
				    JOptionPane.showMessageDialog(null, "Failed to update police status!");
				}
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.setBounds(37, 577, 248, 73);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				passwordField.setText("");
				
				
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btnReset.setBounds(490, 577, 248, 73);
		contentPane.add(btnReset);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to execute the action?", "Confirmation", JOptionPane.YES_NO_OPTION);
				  if (dialogResult == JOptionPane.YES_OPTION) {
					  JOptionPane.showMessageDialog(null, "The Form might be rejected.","Form incomplete", JOptionPane.OK_OPTION);
					  userLogIn uLogin = new userLogIn();
					  uLogin.setVisible(true);
					  dispose();
			 
			        }
				
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		btnCancel.setBounds(924, 577, 248, 73);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("N.I.C");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 77));
		lblNewLabel_2.setBounds(37, 410, 272, 99);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("police update");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 45));
		lblNewLabel_2_1.setBounds(37, 262, 272, 99);
		contentPane.add(lblNewLabel_2_1);
	}
}
