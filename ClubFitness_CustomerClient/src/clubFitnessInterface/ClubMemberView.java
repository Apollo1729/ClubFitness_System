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
		
		
		
		
		clubName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(clubName.getSelectedItem().equals("".trim())) {
					feesField.setSelectedIndex(0);
					memberType.setSelectedIndex(0);
				}
				else if(clubName.getSelectedItem().equals("Club Mercury".trim())) {
					feesField.setSelectedIndex(1);
					memberType.setSelectedIndex(1);
					
				}
				else if(clubName.getSelectedItem().equals("Club Jupiter".trim())) {
					feesField.setSelectedIndex(2);
					memberType.setSelectedIndex(1);
				}
				else if(clubName.getSelectedItem().equals("Club Neptune".trim())) {
					feesField.setSelectedIndex(3);
					memberType.setSelectedIndex(1);
				}
				else if(clubName.getSelectedItem().equals("MultiClub".trim())) {
					feesField.setSelectedIndex(4);
					memberType.setSelectedIndex(2);
				}
			}
		});
		
		
		
		
		
		feesField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(feesField.getSelectedItem().equals(0.00)) {
					clubName.setSelectedIndex(0);
				}
				else if(feesField.getSelectedItem().equals(900.00)) {
					clubName.setSelectedIndex(1);
					
				}
				else if(feesField.getSelectedItem().equals(950.00)) {
					clubName.setSelectedIndex(2);
				}
				else if(feesField.getSelectedItem().equals(1000.00)) {
					clubName.setSelectedIndex(3);
				}
				else if(feesField.getSelectedItem().equals(1200.00)) {
					clubName.setSelectedIndex(4);
				}
				
				
				
			}
		});
		memberType.addActionListener(new ActionListener() {
		    
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(memberType.getSelectedItem().equals("")) {
		            clubName.setSelectedIndex(0);
		        }	
		        else if(memberType.getSelectedItem().equals("MultiClubMember")) {
		            clubName.setSelectedIndex(4); // Set to MultiClub
		        }
		        else if(memberType.getSelectedItem().equals("SingleClubMember")) {
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
