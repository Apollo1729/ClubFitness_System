package clubFitnessInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;




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
		
	
		
		
		
		
		
		frame.setSize(800,900);
		frame.setVisible(true);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	public static void main(String[] args) {
		new ClubMemberView();
	}

}
