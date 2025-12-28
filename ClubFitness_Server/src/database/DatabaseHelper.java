package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {
	
	// Database connection details - update these to match your XAMPP configuration
	private static final String DB_URL = "jdbc:mysql://localhost:3307/clubfitness";
	private static final String DB_USER = "root";  // Default XAMPP username
	private static final String DB_PASSWORD = "";  // Default XAMPP password (empty)
	
	/**
	 * Gets a connection to the database
	 * @return Connection object
	 * @throws SQLException if connection fails
	 */
	private static Connection getConnection() throws SQLException {
		try {
			// Load MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC Driver not found", e);
		}
	}
	
	/**
	 * Registers a new SingleClubMember in the database
	 * @param memberType Type of member
	 * @param name Member's name
	 * @param fees Membership fees
	 * @param clubName Name of the club
	 * @param clubID ID of the club
	 * @return true if registration successful, false otherwise
	 */
	public static Boolean registerSingleClubMember(String memberType, 
			String name, double fees, String clubName, int clubID) {
		
		String sql = "INSERT INTO members (memberType, name, fees, clubName, clubID, membershipPoints) VALUES (?, ?, ?, ?, ?, NULL)";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// Set parameters for the prepared statement
			pstmt.setString(1, memberType);
			pstmt.setString(2, name);
			pstmt.setDouble(3, fees);
			pstmt.setString(4, clubName);
			pstmt.setInt(5, clubID);
			
			// Execute the insert
			int rowsAffected = pstmt.executeUpdate();
			
			// Return true if at least one row was inserted
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			System.err.println("Error registering SingleClubMember: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Registers a new MultiClubMember in the database
	 * @param memberType Type of member
	 * @param name Member's name
	 * @param fees Membership fees
	 * @param clubName Name of the club (should be "MultiClub")
	 * @param membershipPoints Points for the multi-club membership
	 * @return true if registration successful, false otherwise
	 */
	public static Boolean registerMultiClubMember(String memberType, 
			String name, double fees, String clubName, int membershipPoints) {
		
		String sql = "INSERT INTO members (memberType, name, fees, clubName, membershipPoints, clubID) VALUES (?, ?, ?, ?, ?, NULL)";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// Set parameters for the prepared statement
			pstmt.setString(1, memberType);
			pstmt.setString(2, name);
			pstmt.setDouble(3, fees);
			pstmt.setString(4, clubName);
			pstmt.setInt(5, membershipPoints);
			
			// Execute the insert
			int rowsAffected = pstmt.executeUpdate();
			
			// Return true if at least one row was inserted
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			System.err.println("Error registering MultiClubMember: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Searches for a member by their memberID
	 * @param memberID The ID of the member to search for
	 * @return Map containing member details, or null if not found
	 */
	public static Map<String, Object> searchMemberByID(int memberID) {
		
		String sql = "SELECT * FROM members WHERE memberID = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, memberID);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// Member found, create a map with all the details
				Map<String, Object> memberData = new HashMap<>();
				
				memberData.put("memberType", rs.getString("memberType"));
				memberData.put("memberID", rs.getInt("memberID"));
				memberData.put("name", rs.getString("name"));
				memberData.put("fees", rs.getDouble("fees"));
				memberData.put("clubName", rs.getString("clubName"));
				
				// Get optional fields (might be NULL)
				Integer membershipPoints = (Integer) rs.getObject("membershipPoints");
				Integer clubID = (Integer) rs.getObject("clubID");
				
				memberData.put("membershipPoints", membershipPoints);
				memberData.put("clubID", clubID);
				
				return memberData;
			} else {
				// No member found with this ID
				return null;
			}
			
		} catch (SQLException e) {
			System.err.println("Error searching for member: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	// ============================================================================
	// ADD THESE METHODS TO DatabaseHelper.java
	// Location: After the searchMemberByID method, before the closing brace
	// ============================================================================

		/**
		 * Updates an existing member's information in the database
		 * @param memberID The ID of the member to update
		 * @param memberType Type of member
		 * @param name Member's name
		 * @param fees Membership fees
		 * @param clubName Name of the club
		 * @param membershipPoints Points (for MultiClubMember, null for Single)
		 * @param clubID Club ID (for SingleClubMember, null for Multi)
		 * @return true if update successful, false otherwise
		 */
		public static Boolean updateMember(int memberID, String memberType, String name, 
				double fees, String clubName, Integer membershipPoints, Integer clubID) {
			
			String sql = "UPDATE members SET memberType = ?, name = ?, fees = ?, " +
						 "clubName = ?, membershipPoints = ?, clubID = ? WHERE memberID = ?";
			
			try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, memberType);
				pstmt.setString(2, name);
				pstmt.setDouble(3, fees);
				pstmt.setString(4, clubName);
				
				// Set nullable fields
				if (membershipPoints != null) {
					pstmt.setInt(5, membershipPoints);
				} else {
					pstmt.setNull(5, java.sql.Types.INTEGER);
				}
				
				if (clubID != null) {
					pstmt.setInt(6, clubID);
				} else {
					pstmt.setNull(6, java.sql.Types.INTEGER);
				}
				
				pstmt.setInt(7, memberID);
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch (SQLException e) {
				System.err.println("Error updating member: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * Deletes a member from the database
		 * @param memberID The ID of the member to delete
		 * @return true if deletion successful, false otherwise
		 */
		public static Boolean deleteMember(int memberID) {
			
			String sql = "DELETE FROM members WHERE memberID = ?";
			
			try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setInt(1, memberID);
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
			} catch (SQLException e) {
				System.err.println("Error deleting member: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * Retrieves all members from the database
		 * @return List of maps, each containing member details
		 */
		public static java.util.List<Map<String, Object>> viewAllMembers() {
			
			java.util.List<Map<String, Object>> membersList = new java.util.ArrayList<>();
			String sql = "SELECT * FROM members ORDER BY memberID";
			
			try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					Map<String, Object> memberData = new HashMap<>();
					
					memberData.put("memberType", rs.getString("memberType"));
					memberData.put("memberID", rs.getInt("memberID"));
					memberData.put("name", rs.getString("name"));
					memberData.put("fees", rs.getDouble("fees"));
					memberData.put("clubName", rs.getString("clubName"));
					
					// Get optional fields
					Integer membershipPoints = (Integer) rs.getObject("membershipPoints");
					Integer clubID = (Integer) rs.getObject("clubID");
					
					memberData.put("membershipPoints", membershipPoints);
					memberData.put("clubID", clubID);
					
					membersList.add(memberData);
				}
				
				return membersList;
				
			} catch (SQLException e) {
				System.err.println("Error retrieving all members: " + e.getMessage());
				e.printStackTrace();
				return new java.util.ArrayList<>(); // Return empty list on error
			}
		}
}