package emigrationDept;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class adminHashandStore {
	
	
	 private static final int MAX_FAILED_ATTEMPTS = 5;
	    private static final long LOCKOUT_DURATION_MILLIS = 15 * 60 * 1000; // 15 minutes in milliseconds
	    private static final long DELAY_AFTER_WRONG_LOGIN_MILLIS = 15000; // 15 seconds in milliseconds

	
	  public static boolean registerAdmin( String username, String password) {
	        // Hash and salt the password
	        String salt = generateSalt();
	        String hashedPassword = hashPassword(password, salt);

	        if (hashedPassword != null && salt != null) {
	            // Store the user details in the database
	            return storeAdminDetails( username, hashedPassword, salt);
	        } else {
	            
	            return false;
	        }
	    }

	    public static boolean verifyadmin(String username, String password, int iterations) {
	    	
	    	 simulateDelay(5); // 2 seconds delay
	    	  if (isAccountLocked(username)) {
	            JOptionPane.showMessageDialog(null,"Account is locked due to too many failed attempts. Please try again later.");
	              return false;
	          }
	    	
	        // Retrieve the stored hashed password and salt from the database
	        String[] storedCredentials = getPasswordAndSaltFromDatabase(username);

	        if (storedCredentials != null) {
	            String storedHashedPassword = storedCredentials[0];
	            String storedSalt = storedCredentials[1];
	            System.out.println(storedHashedPassword);
           
	            // Hash the input password with the retrieved salt
	            String hashedPasswordString = hashPassword(password, storedSalt);
	            System.out.println(hashedPasswordString);
	            
                boolean isVerified = storedHashedPassword.equals(hashedPasswordString);
	            
	            if (isVerified) {
	                // Reset failed attempts counter upon successful login
	                resetFailedAttempts(username);
	               
	            } else {
	                // Increment failed attempts counter
	                incrementFailedAttempts(username);
	                //JOptionPane.showMessageDialog(null,"Invalid username or password. Please try again.");

	                // Add delay after a wrong password attempt
	                addDelayAfterWrongLogin();
	                
	              
	            }
	            
	            // Compare the hashed input password with the stored hashed password
	            return storedHashedPassword.equals(hashedPasswordString);
	        } else {
	            // Username not found in the database
	          
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
	            System.out.println("Hashed password: " + hashedPasswordString); // Debugging
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

	    private static boolean storeAdminDetails( String username, String hashedPassword, String salt) {
	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "INSERT INTO adminLogin ( username, password, salt) VALUES (?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	               
	                statement.setString(1, username);
	                statement.setString(2, hashedPassword);
	                statement.setString(3, salt);

	                int rowsAffected = statement.executeUpdate();
	                return rowsAffected > 0;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error storing user details in the database: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	    }

	    private static String[] getPasswordAndSaltFromDatabase(String username) {
	    	 simulateDelay(5); // 2 seconds delay
	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "SELECT password, salt FROM adminLogin WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
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
	            System.err.println("Error retrieving admin details from the database: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	
	    private static void simulateDelay(int seconds) {
	        try {
	            TimeUnit.SECONDS.sleep(seconds);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	    
	    public static boolean isAccountLocked(String username) {
	        boolean locked = false;
	        Timestamp lockoutTime = null;
	        
	        unlockAccountIfTimeExpired(username);

	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "SELECT locked, lockout_time FROM adminLogin WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        locked = resultSet.getBoolean("locked");
	                        lockoutTime = resultSet.getTimestamp("lockout_time");
	                    }
	                }
	            }
	            
	            // Check if the account is locked based on the retrieved information
	            if (locked && lockoutTime != null) {
	                // Calculate the time elapsed since the account was locked
	                long lockoutDurationMillis = System.currentTimeMillis() - lockoutTime.getTime();
	                if (lockoutDurationMillis < LOCKOUT_DURATION_MILLIS) {
	                    // Account is still locked
	                    return true;
	                } else {
	                    // Account lockout duration has expired, unlock the account
	                	scheduleUnlock(username);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception
	        }
	        
	        return false;
	    }
	    
	    private static void incrementFailedAttempts(String username) {
	    	Connection connection = databaseConnection.getConnection();
	        String sql = "UPDATE adminLogin SET failed_attempts = failed_attempts + 1 WHERE username = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, username);
	            int rowsAffected = statement.executeUpdate();
	            if (rowsAffected > 0) {
	                // Check if failed attempts exceed the threshold to lock the account
	                if (getFailedAttempts(username) >= MAX_FAILED_ATTEMPTS) {
	                    lockAccount(username);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static void resetFailedAttempts(String username) {
	        // SQL query to update failed_attempts, locked, and lockout_time columns
	        String sql = "UPDATE adminLogin SET failed_attempts = 0, locked = 0, lockout_time = NULL WHERE username = ?";
	        
	        try (Connection connection = databaseConnection.getConnection(); // Assuming you have a method to get the database connection
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Set the username parameter
	            statement.setString(1, username);
	            
	            // Execute the update query
	            statement.executeUpdate();
	            
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static  void addDelayAfterWrongLogin() {
	        try {
	            Thread.sleep(DELAY_AFTER_WRONG_LOGIN_MILLIS); // Sleep for the specified duration
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void unlockAccount(String username) {
	        // Reset the locked status and lockout_time in the database
	        String query = "UPDATE adminLogin SET locked = 0, lockout_time = NULL WHERE username = ?";
	        Connection connection = null;
	        PreparedStatement statement = null;

	        try {
	            connection = databaseConnection.getConnection();
	            statement = connection.prepareStatement(query);
	            statement.setString(1, username);
	            statement.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Account unlocked successfully.");

	            // Create a timer to re-enable locking after the lockout duration
	            Timer timer = new Timer((int) LOCKOUT_DURATION_MILLIS, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    lockAccount(username);
	                }
	            });
	            timer.setRepeats(false); // Only run the timer once
	            timer.start();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            System.out.println("Failed to unlock account.");
	        } finally {
	            // Close resources
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	
	    
	    private static void lockAccount(String username) {
	        // Set locked status and lockout time in the database
	        String query = "UPDATE adminLogin SET locked = 1, lockout_time = ? WHERE username = ?";
	        Connection connection = null;
	        PreparedStatement statement = null;

	        try {
	            connection = databaseConnection.getConnection();
	            statement = connection.prepareStatement(query);
	            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
	            statement.setString(2, username);
	            statement.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Account locked after lockout duration.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            System.out.println("Failed to lock account.");
	        } finally {
	            // Close resources
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    
	    private static int getFailedAttempts(String username) {
	        int failedAttempts = 0;
	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "SELECT failed_attempts FROM adminLogin WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        failedAttempts = resultSet.getInt("failed_attempts");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return failedAttempts;
	    }
	
	    public static void scheduleUnlock(String username) {
	        // Create a ScheduledExecutorService with a single thread
	        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	        // Schedule a task to unlock the account after the lockout duration
	        executor.schedule(() -> {
	            // Automatically unlock the account after lockout duration
	           // unlockAccount(username);
	        	resetFailedAttempts( username);
	        }, LOCKOUT_DURATION_MILLIS, TimeUnit.MILLISECONDS);

	        // Shutdown the executor after all tasks have completed
	        executor.shutdown();
	    }
	  
	    
	    private static void unlockAccountIfTimeExpired( String username) {
			 try( Connection connection = databaseConnection.getConnection()){
			    String query = "SELECT locked, lockout_time FROM adminLogin WHERE username = ?";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setString(1, username);
			        try (ResultSet resultSet = statement.executeQuery()) {
			            if (resultSet.next()) {
			                boolean locked = resultSet.getBoolean("locked");
			                Timestamp lockoutTime = resultSet.getTimestamp("lockout_time");
			                if (locked && lockoutTime != null) {
			                    long lockoutDurationMillis = System.currentTimeMillis() - lockoutTime.getTime();
			                    if (lockoutDurationMillis >= LOCKOUT_DURATION_MILLIS) {
			                    	resetFailedAttempts(username); // Reset lock status if time expired
			                    }
			                }
			            }
			        }
			    }
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	
	
	

}
