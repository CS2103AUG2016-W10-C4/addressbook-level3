package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Lists all persons in the address book to the user.
 */
public class ListByCommand extends NonMutatingCommand {

    public static final String COMMAND_WORD = "listby";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
           + "Displays all persons in the address book as a list sorted by a user specified field.\n\t"
           + "Parameters: \"name\", \"phone\", \"email\" or \"address\".\n\t"
           + "Example: " + COMMAND_WORD + " name";

    public static final String[] VALID_PARAMETERS = new String[] { "name", "phone", "email", "address" };

    private final String key;

    public ListByCommand(String key) {
        this.key = key;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();

        // get rid of immutable list
        List<ReadOnlyPerson> sortablePersons = new ArrayList<>(allPersons);
        switch (key) {
        case "name":
            sortablePersons.sort((a, b) -> a.getName().fullName.compareToIgnoreCase(b.getName().fullName));
            break;
        case "phone":
            sortablePersons.sort((a, b) -> a.getPhone().value.compareToIgnoreCase(b.getPhone().value));
            break;
        case "email":
            sortablePersons.sort((a, b) -> a.getEmail().value.compareToIgnoreCase(b.getEmail().value));
            break;
        case "address":
            sortablePersons.sort((a, b) -> a.getAddress().value.compareToIgnoreCase(b.getAddress().value));
            break;
        default:
            // handled by parser
            break;
        }

        return new CommandResult(getMessageForPersonListShownSummary(sortablePersons), sortablePersons);
    }
}