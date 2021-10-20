package seedu.address.logic.commands.abcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.FileUtil.isFileExists;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.CREATE_ADDRESSBOOK;

import java.nio.file.Path;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AbCreateCommand extends AbCommand {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Create a new address books\n"
            + "Parameters: create {name of the addressbook}\n"
            + "Example: " + COMMAND_WORD + " addressbook2";

    public static final String MESSAGE_ADDRESSBOOK_EXISTS = "Address Book with this name already exists: %1$s";

    public static final String MESSAGE_ADDRESSBOOK_NOT_VALID = "This is not a valid Address Book name: %1$s";

    private final String addressBookName;

    private final Path filePath;

    /**
     * @param filePath the filepath to the address book to create
     */
    public AbCreateCommand(String addressBookName, Path filePath) {
        this.addressBookName = addressBookName;
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isFileExists(filePath)) {
            throw new CommandException(String.format(MESSAGE_ADDRESSBOOK_EXISTS, addressBookName));
        }

        model.setAddressBookFilePath(filePath);

        return new CommandResult(String.format(MESSAGE_CREATE_ADDRESSBOOK_SUCCESS, filePath), CREATE_ADDRESSBOOK);
    }
}
