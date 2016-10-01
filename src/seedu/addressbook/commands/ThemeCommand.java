package seedu.addressbook.commands;

import java.util.StringJoiner;

import seedu.addressbook.ui.MainWindow;

public class ThemeCommand extends NonMutatingCommand {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Lists out all available themes.\n\t"
            + "Example: " + COMMAND_WORD;
    

    @Override
    public CommandResult execute() {
        StringJoiner sj = new StringJoiner(", ");
        for (String theme : MainWindow.getAvailableThemes()) {
            sj.add(theme);
        }
        
        final String output = "Available Themes: \n"
                + "     " + sj.toString() + "\n"
                + "To change theme: Use the --theme=<THEME> "
                + "command line argument when launching the application";
        return new CommandResult(output);
    }

}
