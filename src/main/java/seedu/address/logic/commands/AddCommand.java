package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.function.Function;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_ADDRESS + "ADDRESS "
        + PREFIX_RISKAPPETITE + "RISK APPETITE "
        + PREFIX_DISPOSABLEINCOME + "DISPOSABLE INCOME "
        + PREFIX_CURRENTPLAN + "CURRENT PLAN "
        + PREFIX_LASTMET + "LAST MET "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_RISKAPPETITE + "3 "
        + PREFIX_DISPOSABLEINCOME + "4000 "
        + PREFIX_CURRENTPLAN + "Prudential Proshield "
        + PREFIX_LASTMET + "21-03-2020 "
        + PREFIX_TAG + "friends "
        + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Function<ClientId, Person> toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Function<ClientId, Person> person) {
        // HACK: making person a function is counter-intuitive
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // FIXME: encapsulate 0 within addressbook, orElse("abcd") will cause RuntimeError
        String clientCounter = Optional.ofNullable(model.getAddressBook().getClientCounter()).orElse("0");
        Person person = toAdd.apply(new ClientId(clientCounter));

        // TODO: model.createPerson (to do check and add person)
        if (model.hasPerson(person)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(person);
        // FIXME: model.incrementClientCounter should not be exposed, instead increment directly within addPerson
        model.getAddressBook().incrementClientCounter();
        return new CommandResult(String.format(MESSAGE_SUCCESS, person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.apply(new ClientId("0")).equals(((AddCommand) other)
            .toAdd.apply(new ClientId("0"))));
    }
}
