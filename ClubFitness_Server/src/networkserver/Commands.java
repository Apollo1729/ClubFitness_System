package networkserver;


import database.DatabaseHelper;
import networkserver.Command;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Command interface that all command classes implement.
 * Each command knows how to execute itself by reading data from the client
 * and returning a result.
 */
interface Command {
    Object execute(ObjectInputStream in) throws Exception;
}

/**
 * This class holds all the different commands our server can handle.
 * Each command is a separate inner class that does one specific task.
 */
public class Commands {
	
	
	public static class SingleClubRegisterCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // Read all the registration info the client sent
            String memberType= (String) in.readObject();
            String name = (String) in.readObject();
            double fees = (double) in.readObject();
            String clubName = (String) in.readObject();
            int clubID = (int) in.readObject();
            
            
            return DatabaseHelper.registerSingleClubMember(memberType, name, fees, clubName, clubID);
        }
    }
		
	public static class MultiClubRegisterCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // Read all the registration info the client sent
            String memberType= (String) in.readObject();
            String name = (String) in.readObject();
            double fees = (double) in.readObject();
            String clubName = (String) in.readObject();
            int membershipPoints = (int) in.readObject();
            
            // Fixed: now calling the correct method for MultiClubMember
            return DatabaseHelper.registerMultiClubMember(memberType, name, fees, clubName, membershipPoints);
        }
    }
	
	public static class SearchMemberCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // Read the memberID to search for
            int memberID = (int) in.readObject();
            
            // Search for the member and return the result
            return DatabaseHelper.searchMemberByID(memberID);
        }
    }
	
	public static class UpdateMemberCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // Read all the update info the client sent
            int memberID = (int) in.readObject();
            String memberType = (String) in.readObject();
            String name = (String) in.readObject();
            double fees = (double) in.readObject();
            String clubName = (String) in.readObject();
            Integer membershipPoints = (Integer) in.readObject();
            Integer clubID = (Integer) in.readObject();
            
            return DatabaseHelper.updateMember(memberID, memberType, name, fees, 
            		clubName, membershipPoints, clubID);
        }
    }
	
	public static class DeleteMemberCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // Read the memberID to delete
            int memberID = (int) in.readObject();
            
            return DatabaseHelper.deleteMember(memberID);
        }
    }
	
	public static class ViewAllMembersCommand implements Command {
        @Override
        public Object execute(ObjectInputStream in) throws Exception {
        	
            // No parameters needed for viewing all members
            return DatabaseHelper.viewAllMembers();
        }
    }
		
}