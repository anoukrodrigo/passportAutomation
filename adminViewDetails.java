package emigrationDept;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class adminViewDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	
	 private void fetchDataAndPopulateTable() {
	        try {
	            Connection con = databaseConnection.getConnection();
	            Statement ps = con.createStatement();
	            String sql = "SELECT * FROM application";
	            ResultSet rs = ps.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            DefaultTableModel model = (DefaultTableModel) table.getModel();

	            int cols = rsmd.getColumnCount();
	            String[] colName = new String[cols];

	            for (int i = 0; i < cols; i++) {
	                colName[i] = rsmd.getColumnName(i + 1);
	            }

	            model.setColumnIdentifiers(colName); // Set column names

	            String id;
	            String fullname;
	            String nic;
	            String gender;
	            String email;
	            String address;
	            String tele;
	            String country;
	            String visitDate;
	            String policeStatus;
	            String applicationStatus;

	            // Clear the table before populating
	            model.setRowCount(0);

	            while (rs.next()) {
	                id = rs.getString(1);
	                fullname = rs.getString(2);
	                nic = rs.getString(3);
	                gender = rs.getString(4);
	                email = rs.getString(5);
	                address = rs.getString(6);
	                tele = rs.getString(7);
	                country = rs.getString(8);
	                visitDate = rs.getString(9);
	                policeStatus = rs.getString(10);
	                applicationStatus = rs.getString(11);

	                // Populate the table row by row
	                Object[] rowData = {id, fullname, nic, gender, email, address, tele, country, visitDate, policeStatus, applicationStatus};
	                model.addRow(rowData);
	            }

	            ps.close();
	            con.close();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminViewDetails frame = new adminViewDetails();
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
	public adminViewDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1229, 727);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emigration and Imigration Department of Sri Lanka");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(43, -43, 1162, 145);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 101, 1123, 264);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		
		JButton btnNewButton = new JButton("view");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				fetchDataAndPopulateTable();
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnNewButton.setBounds(21, 554, 213, 70);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("NIC");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1.setBounds(21, 393, 252, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Status");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));
		lblNewLabel_1_1.setBounds(10, 460, 252, 46);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		textField.setBounds(293, 393, 805, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		textField_1.setColumns(10);
		textField_1.setBounds(293, 460, 805, 46);
		contentPane.add(textField_1);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nic = textField.getText();
				String applicationStatus = textField_1.getText();
				
				ApplicationUpdater appUpdate = new ApplicationUpdater();
				boolean update=	appUpdate.updateApplication(nic, applicationStatus);
				
				if (update) {
				   JOptionPane.showMessageDialog(null, "update successfull!");
				   DefaultTableModel model = (DefaultTableModel) table.getModel();
				   model.setRowCount(0); // Clear the table
				   fetchDataAndPopulateTable();
				   
				   
				} else {
					 JOptionPane.showMessageDialog(null, "update unsuccessfull!");
				}
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnUpdate.setBounds(484, 554, 213, 70);
		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				baseFormAppView formApp = new baseFormAppView();
				formApp.setVisible(true);
				dispose();
				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		btnExit.setBounds(976, 554, 213, 70);
		contentPane.add(btnExit);
	}
}
