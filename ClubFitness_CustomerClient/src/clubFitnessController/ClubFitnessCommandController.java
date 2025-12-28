package clubFitnessController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
}
