package networkserver;

import networkserver.Commands.*;
import java.util.HashMap;
import java.util.Map;


/**
 * This class acts as a lookup table for all available commands.
 
 */
public class CommandRegistry {
	
    // Maps action names (like "LOGIN") to their corresponding command objects
    private static Map<String, Command> commands = new HashMap<>();
    
    // static block runs once when the class is loaded
    // This sets up all our commands at startup
    static {
    	    	
        // Register all commands by putting them in the map
        // Key = what the client sends, Value = the command object to run
        

    }
    
    /**
     * Looks up a command by its action name.
     
     */
    public static Command getCommand(String action) {
    	
       	Command command = commands.get(action);
        return command;
    }
}