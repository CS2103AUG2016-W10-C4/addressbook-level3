package seedu.addressbook.data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.addressbook.commands.Command;


/**
 * Storage file which stores all the commands executed by user.
 * Useful for undoing a command later. 
 * 
 * Guarantees
 * -  Every mutable command executed can be found. 
 * @author Govind
 *
 */
public class CommandLog {
    private  ArrayList<String> commandsEntered;
    private static final String LOGGED_COMMAND_MESSAGE="Time:%1$s| User %2$s";
    
    public CommandLog(){
        commandsEntered=new ArrayList<String>();
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
    public ArrayList<String> getCurrentLog(){
        return commandsEntered;
    }
    
    
    public static String getCurrentTime() {
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
    }
    
    /**
     * Logs a command (which changes the addressbook in some way)
     * @param command
     */
    public void log(Command command){
        String actionDone;
        actionDone=String.format(LOGGED_COMMAND_MESSAGE, getCurrentTime(), command.getExecutedAction());
        commandsEntered.add(actionDone);
    }
    
    
    
}
