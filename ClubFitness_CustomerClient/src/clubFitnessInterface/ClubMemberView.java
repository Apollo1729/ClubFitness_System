package clubFitnessInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clubFitnessController.ClubFitnessCommandController;
import models.SingleClubMember;
import models.MultiClubMember;




public class ClubMemberView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel memberType;
	private JLabel memberID;
	private JLabel name;
	private JLabel club;
	private JLabel fees;
	
	private JComboBox <String>membertype;
	private JTextField idField;
	private JTextField nameField;
	private JComboBox<String> clubName;
	private JComboBox<String> feesField;
	
	
	
	private JFrame frame;
	private JTextArea textarea;
	private GridBagConstraints gbc;
	
	private JButton saveButton;
	private JButton searchButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton viewAllButton;
	
	
	
	public ClubMemberView() {
		
		
		
		frame = new JFrame(">>>>>>>>>>>> ClubFitness System <<<<<<<<<<<<<<");
		frame.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();		 
		
		/******Labels********/
	    int bottomPadding = 15;
		memberType = new JLabel("MemberType :");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, bottomPadding,0);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		frame.add(memberType,gbc);
		
		
	    memberID = new JLabel("MemberID :");
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    frame.add(memberID,gbc);
	    
	    name = new JLabel("Name :");
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    frame.add(name,gbc);
	    
	    
	    club = new JLabel("ClubName :");
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    frame.add(club,gbc);
	    
	    fees = new JLabel("ClubFees :");
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    frame.add(fees,gbc);
	    
	    
	    /*******TextFields and ComboBox Options******/
	    String membertypes[] = {"","SingleClubMember","MultiClubMember"};
	    membertype = new JComboBox <String>(membertypes);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,30,0,0);
		frame.add(membertype,gbc);
		
		idField = new JTextField();
		gbc.gridx=1;
		gbc.gridy=1;
		idField.setColumns(12);
		frame.add(idField,gbc);
		
	    nameField = new JTextField();
		gbc.gridx=1;
		gbc.gridy=2;
		nameField.setColumns(12);
		frame.add(nameField,gbc);
		
		String clubNames[] = {"","Club Mercury","Club Jupiter","Club Neptune", "MultiClub"};
		clubName = new JComboBox <String>(clubNames);
		gbc.gridx=1;
		gbc.gridy=3;
		frame.add(clubName,gbc);
		
		String clubfees[] = {"","900.00","950.00","1000.00", "1200.00"};
		feesField= new JComboBox <String>(clubfees);
		gbc.gridx=1;
		gbc.gridy=4;
		frame.add(feesField,gbc);
		
		
		/**********Buttons*******/
		int topPadding = 25;
		saveButton = new JButton("Save");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(topPadding,0,0,0);
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Validate inputs
					String memberType = membertype.getSelectedItem().toString().trim();
					String memberName = nameField.getText().trim();
					String clubname = clubName.getSelectedItem().toString().trim();
					String feesStr = feesField.getSelectedItem().toString().trim();
					
					// Check if required fields are filled
					if(memberType.isEmpty() || memberType.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select a Member Type", "Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(memberName.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please enter Member Name", "Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(clubname.isEmpty() || clubname.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select a Club Name", "Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(feesStr.isEmpty() || feesStr.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select Club Fees", "Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Parse fees
					double fees = Double.parseDouble(feesStr);
					
					int membershipPoints = 100;
					int clubID = 0;
					
					// Determine club ID
					if(clubname.equals("Club Mercury")) {
						clubID = 1;
					}
					else if(clubname.equals("Club Jupiter")) {
			            clubID = 2;
			        }
			        else if(clubname.equals("Club Neptune")) {
			            clubID = 3;
			        }
			        else if(clubname.equals("MultiClub")) {
			            clubID = 4;
			        }
					
					// Save based on member type
					if(memberType.equals("SingleClubMember")) {
						SingleClubMember mem = new SingleClubMember(memberType, memberName, fees, clubname, clubID);
						Boolean success = ClubFitnessCommandController.SingleMemberSaveRecord(mem);
						
						if(success != null && success) {
							// Clear fields after successful save
							clearFields();
						}
					}
					else if(memberType.equals("MultiClubMember")){
						MultiClubMember mem = new MultiClubMember(memberType, memberName, fees, clubname, membershipPoints);
						Boolean success = ClubFitnessCommandController.MultiMemberSaveRecord(mem);
						
						if(success != null && success) {
							// Clear fields after successful save
							clearFields();
						}
					}
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid number format: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		frame.add(saveButton,gbc);
		
		searchButton = new JButton("Search");
		gbc.gridx =0;
		gbc.gridy =5;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(topPadding,80,0,0);
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Get the memberID to search for
					String idText = idField.getText().trim();
					
					if(idText.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please enter a Member ID to search", "Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Parse the memberID
					int searchID = Integer.parseInt(idText);
					
					// Search for the member
					Map<String, Object> memberData = ClubFitnessCommandController.searchMember(searchID);
					
					if(memberData != null) {
						// Member found - populate the fields
						String memberType = (String) memberData.get("memberType");
						String memberName = (String) memberData.get("name");
						double memberFees = (double) memberData.get("fees");
						String memberClubName = (String) memberData.get("clubName");
						Integer membershipPoints = (Integer) memberData.get("membershipPoints");
						Integer clubID = (Integer) memberData.get("clubID");
						
						// Set the member type
						if(memberType.equals("SingleClubMember")) {
							membertype.setSelectedIndex(1);
						} else if(memberType.equals("MultiClubMember")) {
							membertype.setSelectedIndex(2);
						}
						
						// Set the name
						nameField.setText(memberName);
						
						// Set the club name
						if(memberClubName.equals("Club Mercury")) {
							clubName.setSelectedIndex(1);
						} else if(memberClubName.equals("Club Jupiter")) {
							clubName.setSelectedIndex(2);
						} else if(memberClubName.equals("Club Neptune")) {
							clubName.setSelectedIndex(3);
						} else if(memberClubName.equals("MultiClub")) {
							clubName.setSelectedIndex(4);
						}
						
						// Set the fees
						if(memberFees == 900.00) {
							feesField.setSelectedIndex(1);
						} else if(memberFees == 950.00) {
							feesField.setSelectedIndex(2);
						} else if(memberFees == 1000.00) {
							feesField.setSelectedIndex(3);
						} else if(memberFees == 1200.00) {
							feesField.setSelectedIndex(4);
						}
						
						// Display member details in textarea
						StringBuilder details = new StringBuilder();
						details.append("===== MEMBER FOUND =====\n\n");
						details.append("Member ID: ").append(memberData.get("memberID")).append("\n");
						details.append("Member Type: ").append(memberType).append("\n");
						details.append("Name: ").append(memberName).append("\n");
						details.append("Club Name: ").append(memberClubName).append("\n");
						details.append("Fees: $").append(String.format("%.2f", memberFees)).append("\n");
						
						if(membershipPoints != null) {
							details.append("Membership Points: ").append(membershipPoints).append("\n");
						}
						
						if(clubID != null) {
							details.append("Club ID: ").append(clubID).append("\n");
						}
						
						textarea.setText(details.toString());
						
						JOptionPane.showMessageDialog(frame, "Member found successfully!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						// Member not found
						textarea.setText("No member found with ID: " + searchID);
						JOptionPane.showMessageDialog(frame, "No member found with ID: " + searchID, "Search Result", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid Member ID (numbers only)", "Input Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Error during search: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		frame.add(searchButton,gbc);
		
		updateButton = new JButton("Update");
		gbc.gridx =1;
		gbc.gridy =5;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(topPadding,20,0,0);
		
		updateButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							// Get the memberID to update
					String idText = idField.getText().trim();
					
					if(idText.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please search for a member first", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Parse inputs
					int updateID = Integer.parseInt(idText);
					String memberType = membertype.getSelectedItem().toString().trim();
					String memberName = nameField.getText().trim();
					String clubname = clubName.getSelectedItem().toString().trim();
					String feesStr = feesField.getSelectedItem().toString().trim();
					
					// Validate required fields
					if(memberType.isEmpty() || memberType.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select a Member Type", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(memberName.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please enter Member Name", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(clubname.isEmpty() || clubname.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select a Club Name", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(feesStr.isEmpty() || feesStr.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please select Club Fees", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Parse fees
					double fees = Double.parseDouble(feesStr);
					
					// Determine membershipPoints and clubID based on member type
					Integer membershipPoints = null;
					Integer clubID = null;
					
					if(memberType.equals("SingleClubMember")) {
						if(clubname.equals("Club Mercury")) {
							clubID = 1;
						} else if(clubname.equals("Club Jupiter")) {
							clubID = 2;
						} else if(clubname.equals("Club Neptune")) {
							clubID = 3;
						}
					} else if(memberType.equals("MultiClubMember")) {
						membershipPoints = 100; // Default points
					}
					
					// Confirm update
					int confirm = JOptionPane.showConfirmDialog(frame, 
							"Are you sure you want to update member ID: " + updateID + "?", 
							"Confirm Update", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						Boolean success = ClubFitnessCommandController.updateMember(
								updateID, memberType, memberName, fees, clubname, 
								membershipPoints, clubID);
						
						if(success != null && success) {
							// Refresh the display by searching again
							Map<String, Object> memberData = ClubFitnessCommandController.searchMember(updateID);
							if(memberData != null) {
								displayMemberInTextArea(memberData);
							}
						}
					}
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid number format", 
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Error during update: " + ex.getMessage(), 
							"Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});


		frame.add(updateButton,gbc);
		
		deleteButton = new JButton("Delete");
		gbc.gridx =1;
		gbc.gridy =5;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(topPadding,120,0,0);
		// ====================
		// DELETE BUTTON ACTION LISTENER
		// ====================
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Get the memberID to delete
					String idText = idField.getText().trim();
					
					if(idText.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please enter a Member ID to delete", 
								"Validation Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Parse the memberID
					int deleteID = Integer.parseInt(idText);
					
					// Confirm deletion
					int confirm = JOptionPane.showConfirmDialog(frame, 
							"Are you sure you want to delete member ID: " + deleteID + "?\nThis action cannot be undone!", 
							"Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if(confirm == JOptionPane.YES_OPTION) {
						Boolean success = ClubFitnessCommandController.deleteMember(deleteID);
						
						if(success != null && success) {
							// Clear all fields after successful deletion
							clearFields();
							textarea.setText("Member ID " + deleteID + " has been deleted successfully.");
						}
					}
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid Member ID (numbers only)", 
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Error during deletion: " + ex.getMessage(), 
							"Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		frame.add(deleteButton,gbc);
		
		viewAllButton = new JButton("View All");
		gbc.gridx =0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch to fill the width
		gbc.insets = new Insets(topPadding,0,0,0);
		// ====================
		// VIEW ALL BUTTON ACTION LISTENER
		// ====================
		viewAllButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							// Get all members from database
							List<Map<String, Object>> allMembers = ClubFitnessCommandController.viewAllMembers();
							
							if(allMembers == null || allMembers.isEmpty()) {
								textarea.setText("No members found in the database.");
								return;
							}
							
							// Build simple list display
							StringBuilder display = new StringBuilder();
							
							for(Map<String, Object> member : allMembers) {
								int memberID = (int) member.get("memberID");
								String memberType = (String) member.get("memberType");
								String name = (String) member.get("name");
								String clubName = (String) member.get("clubName");
								double fees = (double) member.get("fees");
								Integer membershipPoints = (Integer) member.get("membershipPoints");
								Integer clubID = (Integer) member.get("clubID");
								
								// Build the line
								display.append("MemberID: ").append(memberID)
								       .append(", Name: ").append(name)
								       .append(", Type: ").append(memberType)
								       .append(", Club: ").append(clubName)
								       .append(", Fees: $").append(String.format("%.2f", fees));
								
								// Add membershipPoints or clubID depending on type
								if(membershipPoints != null) {
									display.append(", Points: ").append(membershipPoints);
								}
								
								if(clubID != null) {
									display.append(", ClubID: ").append(clubID);
								}
								
								display.append("\n");
							}
							
							textarea.setText(display.toString());
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(frame, "Error viewing all members: " + ex.getMessage(), 
									"Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					}
				});

		frame.add(viewAllButton,gbc);
		
		
		
		textarea = new JTextArea(15,50);
		gbc.gridx =0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		frame.add(textarea,gbc);
	    
		 
		
		
		
		clubName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(clubName.getSelectedItem().equals("".trim())) {
					feesField.setSelectedIndex(0);
					membertype.setSelectedIndex(0);
				}
				else if(clubName.getSelectedItem().equals("Club Mercury".trim())) {
					feesField.setSelectedIndex(1);
					membertype.setSelectedIndex(1);
					
				}
				else if(clubName.getSelectedItem().equals("Club Jupiter".trim())) {
					feesField.setSelectedIndex(2);
					membertype.setSelectedIndex(1);
				}
				else if(clubName.getSelectedItem().equals("Club Neptune".trim())) {
					feesField.setSelectedIndex(3);
					membertype.setSelectedIndex(1);
				}
				else if(clubName.getSelectedItem().equals("MultiClub".trim())) {
					feesField.setSelectedIndex(4);
					membertype.setSelectedIndex(2);
				}
			}
		});
		
		
		
		
		
		feesField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(feesField.getSelectedItem().equals("0.00")) {
					clubName.setSelectedIndex(0);
				}
				else if(feesField.getSelectedItem().equals("900.00")) {
					clubName.setSelectedIndex(1);
					
				}
				else if(feesField.getSelectedItem().equals("950.00")) {
					clubName.setSelectedIndex(2);
				}
				else if(feesField.getSelectedItem().equals("1000.00")) {
					clubName.setSelectedIndex(3);
				}
				else if(feesField.getSelectedItem().equals("1200.00")) {
					clubName.setSelectedIndex(4);
				}
				
				
				
			}
		});
		membertype.addActionListener(new ActionListener() {
		    
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(membertype.getSelectedItem().equals("")) {
		            clubName.setSelectedIndex(0);
		        }	
		        else if(membertype.getSelectedItem().equals("MultiClubMember")) {
		            clubName.setSelectedIndex(4); // Set to MultiClub
		        }
		        else if(membertype.getSelectedItem().equals("SingleClubMember")) {
		            // If MultiClub or blank is currently selected, default to Club Mercury
		            if(clubName.getSelectedIndex() == 4 || clubName.getSelectedIndex() == 0) {
		                clubName.setSelectedIndex(1); // Set to Club Mercury as default
		            }
		            // Otherwise keep the current single club selection (Mercury, Jupiter, or Neptune)
		        }
		    }
		});
		
	
		
		
		
		
		
		frame.setSize(1000,1000);
		frame.setVisible(true);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	/**
	 * Helper method to clear all input fields after successful save
	 */
	private void clearFields() {
		membertype.setSelectedIndex(0);
		nameField.setText("");
		clubName.setSelectedIndex(0);
		feesField.setSelectedIndex(0);
		idField.setText("");
		textarea.setText("");
	}
	
	private void displayMemberInTextArea(Map<String, Object> memberData) {
		StringBuilder details = new StringBuilder();
		details.append("===== MEMBER DETAILS =====\n\n");
		details.append("Member ID: ").append(memberData.get("memberID")).append("\n");
		details.append("Member Type: ").append(memberData.get("memberType")).append("\n");
		details.append("Name: ").append(memberData.get("name")).append("\n");
		details.append("Club Name: ").append(memberData.get("clubName")).append("\n");
		details.append("Fees: $").append(String.format("%.2f", (double)memberData.get("fees"))).append("\n");
		
		Integer membershipPoints = (Integer) memberData.get("membershipPoints");
		if(membershipPoints != null) {
			details.append("Membership Points: ").append(membershipPoints).append("\n");
		}
		
		Integer clubID = (Integer) memberData.get("clubID");
		if(clubID != null) {
			details.append("Club ID: ").append(clubID).append("\n");
		}
		
		textarea.setText(details.toString());
	}
	
	public static void main(String[] args) {
		new ClubMemberView();
	}

}