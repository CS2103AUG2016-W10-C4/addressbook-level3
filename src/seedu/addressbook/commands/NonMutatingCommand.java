package seedu.addressbook.commands;

/**
 * A command that does not mutate the AddressBook
 *
 */
public abstract class NonMutatingCommand extends Command {
    public NonMutatingCommand() {
        super();
    }
    
    public NonMutatingCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public String getExecutedAction() {
        return null;
    }

}
