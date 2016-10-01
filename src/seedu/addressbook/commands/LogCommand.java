package seedu.addressbook.commands;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

import seedu.addressbook.data.LogEntry;

public class LogCommand extends NonMutatingCommand {

    public static final String COMMAND_WORD = "log";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "View a log of all changes to the address book in this session\n\t"
            + "Example: " + COMMAND_WORD;

    private static final String LOG_PREFIX = "Address Book Log\n"
            + "These are all the changes made to the address book in the current session\n\n";

    private static final String EMPTY_LOG_MESSAGE = "You have not made any changes to the address book yet";

    private static final String LOG_ENTRY_FORMAT = "%1$s: %2$s";
    
    /**
     * Returns the amount of time passed since the provided LocalDateTime in a human 
     * readable format 
     */
    private String timeAgo(LocalDateTime time) {
        long seconds = Duration.between(time, LocalDateTime.now()).getSeconds();
        
        if (seconds < 60) {
            return String.format("%ds ago", seconds);
        } else if (seconds < 120) {
            return "A minute ago";
        } else if (seconds < 60 * 60) {
            return String.format("%d min ago", seconds / 60);
        } else if (seconds < 2 * 60 * 60) {
            return "An hour ago";
        } else if (seconds < 24 * 60 * 60) {
            return String.format("%d hours ago", seconds / (60 * 60));
        }
        
        return "A long time ago, in a galaxy far far away";
    }
    
    /**
     * Formats the provided logEntry into a human readable string
     */
    private String formatEntry(LogEntry entry) {
        String time = this.timeAgo(entry.getTime());
        return String.format(LogCommand.LOG_ENTRY_FORMAT, time, entry.getAction());
    }
    
    @Override
    public CommandResult execute() {
        List<LogEntry> logEntries = commandLog.getCurrentLog();
        String output; 
        
        if (logEntries.isEmpty()) {
            output = LogCommand.EMPTY_LOG_MESSAGE;
        } else {
            StringJoiner sj = new StringJoiner("\n");
            for (LogEntry entry : logEntries) {
                sj.add(this.formatEntry(entry));
            }
            
            output = sj.toString();
        }
        
        return new CommandResult(LogCommand.LOG_PREFIX + output);
    }
}
