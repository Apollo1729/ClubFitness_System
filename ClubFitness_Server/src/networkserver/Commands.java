package networkserver;


import database.DatabaseHelper;
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
	
		
		
}
