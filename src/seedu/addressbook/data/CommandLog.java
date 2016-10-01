package seedu.addressbook.data;

import java.util.ArrayList;

import seedu.addressbook.commands.Command;


/**
 * Storage file which stores all the commands executed by user.
 * Useful for undoing a command later. 
 */
public class CommandLog {
    private ArrayList<LogEntry> commandsEntered;
    
    public CommandLog() {
        commandsEntered = new ArrayList<LogEntry>();
    }
    
    /**
     * Clears the command log
     */
    public void clear(){
        commandsEntered.clear();
    }
    
    /**
     * @return list of commands entered so far
     */
    public ArrayList<LogEntry> getCurrentLog(){
        return commandsEntered;
    }
    
    /**
     * Logs a mutating AddressBook command
     * @param command
     */
    public void log(Command command) {
        commandsEntered.add(new LogEntry(command.getExecutedAction()));
    }
}
