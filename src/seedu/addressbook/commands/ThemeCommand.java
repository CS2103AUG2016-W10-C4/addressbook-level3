package seedu.addressbook.commands;

import java.util.StringJoiner;

import seedu.addressbook.ui.MainWindow;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Lists out all available themes.\n\t"
            + "Example: " + COMMAND_WORD;
    

    @Override
    public CommandResult execute() {
        StringJoiner sj = new StringJoiner(", ", "Available Themes: \n", "");
        for (String theme : MainWindow.getAvailableThemes()) {
            sj.add(theme);
        }
        
        return new CommandResult(sj.toString());
    }

}
