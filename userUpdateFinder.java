package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userUpdateFinder {
	 public boolean findStatusUser(String nic, String password) {
	        String sql = "SELECT ua.applicationStatus ,ua.fullName FROM userInfor ui INNER JOIN application ua ON ui.nic = ua.nic WHERE ui.nic = ? AND ui.password = ?";
                        //SELECT ua.applicationStatus, ua.fullName FROM userInfo ui INNER JOIN application ua ON ui.nic = ua.nic WHERE ui.nic = ? AND ui.password = ?; 
	        // Verify user details to get the hashed password
	        String hashedPassword = UserStatushashPassword.verifyUserDetails(nic, password);

	        // Proceed if the verification is successful
	   
	        if (hashedPassword != null) {
	            Connection con = databaseConnection.getConnection();
	            try {
	                PreparedStatement ps = con.prepareStatement(sql);
	                ps.setString(1, nic);
	                ps.setString(2, hashedPassword);

	             
	                ResultSet rs = ps.executeQuery();
	                
	                
	             
	                if (rs.next()) {
	                
	                    String applicationStatus = rs.getString("applicationStatus");
	                    String fullName = rs.getString("fullName");
	                    System.out.println("app"+applicationStatus);
	                    System.out.println("app"+fullName);
	                 
	                
	                    
	               
	                    
	                  

	                  
	                    rs.close();
	                    ps.close();
	                    con.close();
	                    
	                    
	                    

	                    return true;
	                } else {
	                 
	                    return false;
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return false;
	            }
	        } else {
	         
	            return false;
	        }
	    }
	 
	 
	
	

}
