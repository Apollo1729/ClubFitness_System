package clubFitnessController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import javax.swing.JOptionPane;


import models.MultiClubMember;
import models.SingleClubMember;
	
public class ClubFitnessCommandController {
	
	private final static String SERVERHOST = "localhost";
	private final static int SERVERPORT = 5000;
	
	public static Boolean MultiMemberSaveRecord(MultiClubMember clubmember) {
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){	
			out.writeObject("SAVEMULTICLUBMEMBER");
			out.flush();
			out.writeObject(clubmember.getMemberType());
			out.flush();
			out.writeObject(clubmember.getName());
			out.flush();
			out.writeObject(clubmember.getFees());
			out.flush();
			out.writeObject(clubmember.getClubName());
			out.flush();
			out.writeObject(clubmember.getMembershipPoints());
			out.flush();
			
			
			Object response = in.readObject();
			if(response instanceof Boolean) {
				Boolean success = (Boolean) response;
				JOptionPane.showMessageDialog(null,"Record Saved Successfully", "Save Record",JOptionPane.INFORMATION_MESSAGE);
				return success;
					
			}else {
				JOptionPane.showMessageDialog(null,"Error: -> Record not save successfully...","Save Record",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}catch(Exception e) {
			e.getMessage();
			
		}
			
		return null;
	}
	
	public static Boolean SingleMemberSaveRecord(SingleClubMember clubmember) {
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){
			out.writeObject("SAVESINGLECLUBMEMBER");
			out.flush();
			out.writeObject(clubmember.getMemberType());
			out.flush();
			out.writeObject(clubmember.getName());
			out.flush();
			out.writeObject(clubmember.getFees());
			out.flush();
			out.writeObject(clubmember.getClubName());
			out.flush();
			out.writeObject(clubmember.getClubID());
			out.flush();
			
			
			Object response = in.readObject();
			if(response instanceof Boolean) {
				Boolean success = (Boolean) response;
				JOptionPane.showMessageDialog(null,"Record Saved Successfully", "Save Record",JOptionPane.INFORMATION_MESSAGE);
				return success;			
			}else {
				JOptionPane.showMessageDialog(null,"Error:--> Record not save successfully...","Save Record",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}catch(Exception e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null,"Error: "+ e.getMessage() ,"Save Record",JOptionPane.INFORMATION_MESSAGE);
		}
			
		return null;
	}
	
	/**
	 * Searches for a member by memberID
	 * @param memberID The ID of the member to search for
	 * @return Map containing member data, or null if not found
	 */
	public static Map<String, Object> searchMember(int memberID) {
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){
			
			// Send the search command
			out.writeObject("SEARCHMEMBER");
			out.flush();
			
			// Send the memberID to search for
			out.writeObject(memberID);
			out.flush();
			
			// Receive the response
			Object response = in.readObject();
			
			if(response instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> memberData = (Map<String, Object>) response;
				return memberData;
			} else {
				// Member not found
				return null;
			}
			
		} catch(Exception e) {
			System.err.println("Error searching for member: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
		}
			
		return null;
	}
	/**
	 * Updates an existing member's information
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
		
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){
			
			// Send the update command
			out.writeObject("UPDATEMEMBER");
			out.flush();
			
			// Send all the member data
			out.writeObject(memberID);
			out.flush();
			out.writeObject(memberType);
			out.flush();
			out.writeObject(name);
			out.flush();
			out.writeObject(fees);
			out.flush();
			out.writeObject(clubName);
			out.flush();
			out.writeObject(membershipPoints);
			out.flush();
			out.writeObject(clubID);
			out.flush();
			
			// Receive the response
			Object response = in.readObject();
			
			if(response instanceof Boolean) {
				Boolean success = (Boolean) response;
				if(success) {
					JOptionPane.showMessageDialog(null, "Record Updated Successfully", 
							"Update Record", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Record not updated", 
							"Update Record", JOptionPane.ERROR_MESSAGE);
				}
				return success;
			}
			
		} catch(Exception e) {
			System.err.println("Error updating member: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
					"Update Error", JOptionPane.ERROR_MESSAGE);
		}
			
		return null;
	}
	
	/**
	 * Deletes a member by their memberID
	 * @param memberID The ID of the member to delete
	 * @return true if deletion successful, false otherwise
	 */
	public static Boolean deleteMember(int memberID) {
		
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){
			
			// Send the delete command
			out.writeObject("DELETEMEMBER");
			out.flush();
			
			// Send the memberID to delete
			out.writeObject(memberID);
			out.flush();
			
			// Receive the response
			Object response = in.readObject();
			
			if(response instanceof Boolean) {
				Boolean success = (Boolean) response;
				if(success) {
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully", 
							"Delete Record", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Error: Record not deleted or not found", 
							"Delete Record", JOptionPane.ERROR_MESSAGE);
				}
				return success;
			}
			
		} catch(Exception e) {
			System.err.println("Error deleting member: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
					"Delete Error", JOptionPane.ERROR_MESSAGE);
		}
			
		return null;
	}
	
	/**
	 * Retrieves all members from the database
	 * @return List of maps containing all member data, or empty list if error
	 */
	@SuppressWarnings("unchecked")
	public static java.util.List<java.util.Map<String, Object>> viewAllMembers() {
		
		try(Socket socket = new Socket(SERVERHOST, SERVERPORT);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
			){
			
			// Send the viewAll command
			out.writeObject("VIEWALLMEMBERS");
			out.flush();
			
			// Receive the response
			Object response = in.readObject();
			
			if(response instanceof java.util.List) {
				return (java.util.List<java.util.Map<String, Object>>) response;
			}
			
		} catch(Exception e) {
			System.err.println("Error viewing all members: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
					"View All Error", JOptionPane.ERROR_MESSAGE);
		}
			
		return new java.util.ArrayList<>(); // Return empty list on error
	}
}