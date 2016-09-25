package seedu.addressbook;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;
import seedu.addressbook.common.ArgParser;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.ui.Gui;
import seedu.addressbook.ui.Stoppable;

/**
 * Main entry point to the application.
 */
public class Main extends Application implements Stoppable{

    /** Version info of the program. */
    public static final String VERSION = "AddessBook Level 3 - Version 1.0";
    
    private static ArgParser args;
    private Gui gui;

    @Override
    public void start(Stage primaryStage) throws Exception{
        gui = new Gui(new Logic(), VERSION);
        gui.start(primaryStage, this, Main.args.get("theme"));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        Main.args = new ArgParser(args);
        launch(args);
    }
}


