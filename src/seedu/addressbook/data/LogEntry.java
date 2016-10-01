package seedu.addressbook.data;

import java.time.LocalDateTime;

/**
 * Represents a single entry in the command log
 */
public class LogEntry {
    private String action; 
    private LocalDateTime time = LocalDateTime.now();
    
    public LogEntry(String action) {
        this.action = action; 
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
