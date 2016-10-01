package seedu.addressbook.commands;

import java.util.List;
import java.util.StringJoiner;

public class LogCommand extends NonMutatingCommand {

    public static final String COMMAND_WORD = "log";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "View a log of all changes to the address book in this session\n\t"
            + "Example: " + COMMAND_WORD;

    private static final String LOG_PREFIX = "Address Book Log\n"
            + "These are all the changes made to the address book in the current session\n\n";

    private static final String EMPTY_LOG_MESSAGE = "You have not made any changes to the address book yet";
    
    @Override
    public CommandResult execute() {
        List<String> logEntries = commandLog.getCurrentLog();
        String output; 
        
        if (logEntries.isEmpty()) {
            output = LogCommand.EMPTY_LOG_MESSAGE;
        } else {
            StringJoiner sj = new StringJoiner("\n");
            for (String logEntry : logEntries) {
                sj.add(logEntry);
            }
            
            output = sj.toString();
        }
        
        
        return new CommandResult(LogCommand.LOG_PREFIX + output);
    }
}
