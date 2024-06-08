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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dateVerification extends JFrame {

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
					dateVerification frame = new dateVerification();
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
	public dateVerification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1217, 706);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(10, -36, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select a Date for Appointment ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 54));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(74, 96, 1078, 139);
		contentPane.add(lblNewLabel_1);
		
	
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("2024/01/18");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/01/18";
				
				
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton.setBounds(42, 257, 226, 63);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("2024/06/18");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/06/18";
				
				
				
			}
		});
		rdbtnNewRadioButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_1.setBounds(42, 358, 226, 63);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("2024/02/18");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String visitDate = "2024/02/18";
				
				
			}
		});
		rdbtnNewRadioButton_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_2.setBounds(359, 257, 226, 63);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("2024/09/18");
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/09/18";
				
				
			}
		});
		rdbtnNewRadioButton_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_3.setBounds(359, 358, 226, 63);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("2024/03/28");
		rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/03/28";
				
			}
		});
		rdbtnNewRadioButton_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_4.setBounds(670, 257, 226, 63);
		contentPane.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("2024/10/20");
		rdbtnNewRadioButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String visitDate = "2024/10/20";
				
				
				
			}
		});
		rdbtnNewRadioButton_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_5.setBounds(670, 358, 226, 63);
		contentPane.add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("2024/05/13");
		rdbtnNewRadioButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/05/13";
				
			}
		});
		rdbtnNewRadioButton_6.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_6.setBounds(946, 257, 226, 63);
		contentPane.add(rdbtnNewRadioButton_6);
		
		JRadioButton rdbtnNewRadioButton_7 = new JRadioButton("2024/12/18");
		rdbtnNewRadioButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String visitDate = "2024/12/18";
				
			}
		});
		rdbtnNewRadioButton_7.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		rdbtnNewRadioButton_7.setBounds(946, 358, 226, 63);
		contentPane.add(rdbtnNewRadioButton_7);
		
		ButtonGroup bt = new ButtonGroup();
		bt.add( rdbtnNewRadioButton);
		bt.add( rdbtnNewRadioButton_1);
		bt.add( rdbtnNewRadioButton_2);
		bt.add( rdbtnNewRadioButton_3);
		bt.add( rdbtnNewRadioButton_4);
		bt.add( rdbtnNewRadioButton_5);
		bt.add( rdbtnNewRadioButton_6);
		bt.add( rdbtnNewRadioButton_7);
		
		
		
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 42));
		passwordField.setBounds(379, 473, 795, 82);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ni =  passwordField.getText().length();
				
				String nic = passwordField.getText();
				String visitDate = null;
				
				if(rdbtnNewRadioButton.isSelected()) {
					 visitDate = "2024/01/18";
				}
				else if(rdbtnNewRadioButton_1.isSelected()) {
					visitDate = "2024/06/18";
					 
					 
				}
				else if(rdbtnNewRadioButton_2.isSelected()) {
					visitDate = "2024/02/18";
					
				}
             else if(rdbtnNewRadioButton_3.isSelected()) {
            	 visitDate = "2024/09/18";
					
				}
                 else if(rdbtnNewRadioButton_4.isSelected()) {
                	 visitDate = "2024/03/28";
	
                                      }
                     else if(rdbtnNewRadioButton_5.isSelected()) {
                    	 visitDate = "2024/10/20";
	
         }
                         else if(rdbtnNewRadioButton_6.isSelected()) {
                  
                        	 visitDate = "2024/05/13";
                                  }
                         else  {
                        	 visitDate = "2024/12/18";

	
                                  }
				
				if (bt.getSelection() == null && ni==0 ) {
                    JOptionPane.showMessageDialog(null, "Please please in all fields");
                } else {
                    // Both radio button and combo box are selected, proceed with the action
                
                
			  
				
				  DatabaseUpdaterUser updater = new DatabaseUpdaterUser();
				  
				  boolean updateSuccess = updater.updateRecord(nic, visitDate);
			        if (updateSuccess) {
			            JOptionPane.showMessageDialog(null, "Database updated successfully!");
			            policeUpdateForm policeup = new policeUpdateForm();
			            policeup.setVisible(true);
			            dispose();
			            
			        } else {
			            JOptionPane.showMessageDialog(null, "Failed to update database!");
			        }
                }
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnNewButton.setBounds(29, 578, 285, 63);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				  bt.clearSelection(); 
				
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnReset.setBounds(443, 578, 285, 63);
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
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnCancel.setBounds(887, 578, 285, 63);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("N.I.C");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 65));
		lblNewLabel_2.setBounds(42, 473, 277, 73);
		contentPane.add(lblNewLabel_2);
	}
}
