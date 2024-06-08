package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdaterUser {
	
	 // Method to update the database with NIC and date
	   // Define the updateRecord method to update the database with NIC and date
    public boolean updateRecord(String nic, String visitDate) {
        boolean success = false;
        try {
            Connection con = databaseConnection.getConnection();
            String sql = "UPDATE application SET visitDate = ? WHERE nic = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, visitDate);
            ps.setString(2, nic);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


	
	
	

}
