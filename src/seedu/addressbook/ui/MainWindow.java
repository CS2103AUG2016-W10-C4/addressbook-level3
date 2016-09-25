package seedu.addressbook.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;
    
    private static final String[] COMMON_STYLESHEETS = {"Fonts.css", "Common.css"};
    private static final String[] AVAILABLE_THEMES = {"dark", "light"};
    private static final String DEFAULT_THEME = "dark";

    public MainWindow(){
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    private void initialize() {
        for (String stylesheet : MainWindow.COMMON_STYLESHEETS) {
            this.container.getStylesheets().add(MainWindow.getAssetPath(stylesheet));
        }        
        this.setTheme(MainWindow.DEFAULT_THEME);
    }

    @FXML
    private TextArea outputConsole;

    @FXML
    private TextField commandInput;
    
    @FXML
    private VBox container;

    @FXML
    void onCommand(ActionEvent event) {
        try {
            String userCommandText = commandInput.getText();
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /** Clears the output display area */
    public void clearOutputConsole(){
        outputConsole.clear();
    }

    /** Displays the result of a command execution to the user. */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if(resultPersons.isPresent()) {
            display(resultPersons.get());
        }
        display(result.feedbackToUser);
    }

    public void displayWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        display(MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo);
    }
    
    /** 
     * Sets the current theme of the application.
     * @return true if the theme was set successfully, false otherwise (because it is not a valid theme)  
     */
    public boolean setTheme(String theme) {
        if (theme == null) {
            return false;
        }
        
        if (Arrays.asList(MainWindow.AVAILABLE_THEMES).contains(theme.toLowerCase())) {
            this.container.getStylesheets().add(MainWindow.getThemePath(theme));
            return true;
        }
        
        return false;
    }
    
    private static String getThemePath(String theme) {
        String fileName = theme.substring(0, 1).toUpperCase() + theme.substring(1) + "Theme.css";
        return MainWindow.getAssetPath(fileName);
    }
    
    public static String[] getAvailableThemes() {
        return MainWindow.AVAILABLE_THEMES;
    }
    
    /** 
     * Returns the full path to the file in the ui folder as a string. 
     * Do not include a slash in front of file name. 
     */
    private static String getAssetPath(String file) {
        return "/seedu/addressbook/ui/" + file;
    }

    /**
     * Displays the list of persons in the output display area, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void display(List<? extends ReadOnlyPerson> persons) {
        display(new Formatter().format(persons));
    }

    /**
     * Displays the given messages on the output display area, after formatting appropriately.
     */
    private void display(String... messages) {
        outputConsole.setText(outputConsole.getText() + new Formatter().format(messages));
    }

}
