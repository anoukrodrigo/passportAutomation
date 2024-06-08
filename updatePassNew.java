package emigrationDept;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.JOptionPane;

public class updatePassNew {
	
	
	  public static boolean updateUser( String nic, String password) {
	        // Hash and salt the password
	        String salt = generateSalt();
	        String hashedPassword = hashPassword(password, salt);
	        System.out.println("hashedPassword"+hashedPassword);
         
	        	  if (hashedPassword != null && salt != null) {
	  	            // Store the user details in the database
	  	            return storeAdminDetails( nic, hashedPassword, salt);
	  	        } else {
	  	            
	  	            return false;
	  	        }
	        	
	        }
	      
	    
	  
	
	    
	  
	   private static String hashPassword(String password, String salt) {
	        try {
	            if (password == null || salt == null) {
	                System.err.println("Password or salt is null.");
	                return null;
	            }

	            // Concatenate password and salt
	            String saltedPassword = password + salt;

	            // Hash the salted password
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

	            // Return the hashed password
	            String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
	          //  System.out.println("Hashed password: " + hashedPasswordString); // Debugging
	            return hashedPasswordString;
	        } catch (NoSuchAlgorithmException e) {
	            System.err.println("Hashing algorithm not found.");
	            e.printStackTrace();
	            return null;
	        }
	    }


	    private static String generateSalt() {
	        byte[] salt = new byte[16];
	        new SecureRandom().nextBytes(salt);
	        return Base64.getEncoder().encodeToString(salt);
	    }
	    
	    private static boolean storeAdminDetails( String nic, String hashedPassword, String salt) {
	    	 try (Connection connection = databaseConnection.getConnection()) {
	    	        // Check if the user exists based on NIC
	    	    
	    	            String query = "UPDATE userInfor SET password = ?, salt = ? WHERE nic = ?";
	    	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	    	                statement.setString(1, hashedPassword);
	    	                statement.setString(2, salt);
	    	                statement.setString(3, nic);

	    	                int rowsAffected = statement.executeUpdate();
	    	                return rowsAffected > 0;
	    	            }
	    	        
	    	    } catch (SQLException e) {
	    	        System.err.println("Error updating user password and salt in the database: " + e.getMessage());
	    	        e.printStackTrace();
	    	        return false;
	    	    }
	        
	        
	    }
	
	
	    private static String[] getPasswordAndSaltFromDatabase(String nic) {
	    	
	        try (Connection connection = databaseConnection.getConnection()) {
	        	
	            String query = "SELECT password, salt FROM userInfor WHERE nic = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, nic);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        String storedHashedPassword = resultSet.getString("password");
	                        String storedSalt = resultSet.getString("salt");
	                       
	                        return new String[]{storedHashedPassword, storedSalt};
	                    } else {
	                        // Username not found
	                        return null;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error retrieving user details from the database: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	    
	  


}
