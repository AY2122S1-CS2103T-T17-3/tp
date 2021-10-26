package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREATEDAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_CLIENTID_AMY = "9";
    public static final String VALID_CLIENTID_BOB = "10";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BIRTHDAY_AMY = "24-09-1999";
    public static final String VALID_BIRTHDAY_BOB = "24-09-1999";
    public static final String VALID_LASTMET_AMY = "24-09-2021";
    public static final String VALID_LASTMET_BOB = "24-09-2021";
    public static final String VALID_RISKAPPETITE_AMY = "3";
    public static final String VALID_RISKAPPETITE_BOB = "4";
    public static final String VALID_DISPOSABLEINCOME_AMY = "300";
    public static final String VALID_DISPOSABLEINCOME_BOB = "400";
    public static final String VALID_NEXTMEETING_DATE_AMY = "24-09-2051";
    public static final String VALID_NEXTMEETING_TIME_AMY = "12:00";
    public static final String VALID_NEXTMEETING_LOCATION_AMY = "Zoom";
    public static final String VALID_NEXTMEETING_DATE_BOB = "24-09-2051";
    public static final String VALID_NEXTMEETING_TIME_BOB = "12:00";
    public static final String VALID_NEXTMEETING_LOCATION_BOB = "Zoom";
    public static final String VALID_CREATEDAT_AMY = "24-09-2020";
    public static final String VALID_CREATEDAT_BOB = "24-09-2020";
    public static final String VALID_CURRENTPLAN_AMY = "Prudential PRUwealth";
    public static final String VALID_CURRENTPLAN_BOB = "Prudential PROwealth";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String CLIENTID_DESC_AMY = " " + PREFIX_CLIENTID + VALID_CLIENTID_AMY;
    public static final String CLIENTID_DESC_BOB = " " + PREFIX_CLIENTID + VALID_CLIENTID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String RISKAPPETITE_DESC_AMY = " " + PREFIX_RISKAPPETITE + VALID_RISKAPPETITE_AMY;
    public static final String RISKAPPETITE_DESC_BOB = " " + PREFIX_RISKAPPETITE + VALID_RISKAPPETITE_BOB;
    public static final String DISPOSABLEINCOME_DESC_AMY = " " + PREFIX_DISPOSABLEINCOME + VALID_DISPOSABLEINCOME_AMY;
    public static final String DISPOSABLEINCOME_DESC_BOB = " " + PREFIX_DISPOSABLEINCOME + VALID_DISPOSABLEINCOME_BOB;
    public static final String LASTMET_DESC_AMY = " " + PREFIX_LASTMET + VALID_LASTMET_AMY;
    public static final String LASTMET_DESC_BOB = " " + PREFIX_LASTMET + VALID_LASTMET_BOB;
    public static final String CURRENTPLAN_DESC_AMY = " " + PREFIX_CURRENTPLAN + VALID_CURRENTPLAN_AMY;
    public static final String CURRENTPLAN_DESC_BOB = " " + PREFIX_CURRENTPLAN + VALID_CURRENTPLAN_BOB;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + " "; // empty string not allowed
    public static final String INVALID_RISKAPPETITE_DESC = " " + PREFIX_RISKAPPETITE + "6"; // beyond RA range
    public static final String INVALID_DISPOSABLEINCOME_DESC = " " + PREFIX_DISPOSABLEINCOME + "-2"; // negative integer
    public static final String INVALID_LASTMET_DESC = " " + PREFIX_LASTMET + "2020-Jul-23"; // String in date
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditClientDescriptor DESC_AMY;
    public static final EditCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBirthday(VALID_BIRTHDAY_AMY)
                .withRiskAppetite(VALID_RISKAPPETITE_AMY).withDisposableIncome(VALID_DISPOSABLEINCOME_AMY)
                .withCurrentPlan(VALID_CURRENTPLAN_AMY).withLastMet(VALID_LASTMET_AMY)
                .withNextMeeting(VALID_NEXTMEETING_DATE_AMY, VALID_NEXTMEETING_TIME_AMY, VALID_NEXTMEETING_TIME_AMY,
                    VALID_NEXTMEETING_LOCATION_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withBirthday(VALID_BIRTHDAY_AMY)
                .withRiskAppetite(VALID_RISKAPPETITE_BOB).withDisposableIncome(VALID_DISPOSABLEINCOME_BOB)
                .withCurrentPlan(VALID_CURRENTPLAN_BOB).withLastMet(VALID_LASTMET_BOB)
                .withNextMeeting(VALID_NEXTMEETING_DATE_BOB, VALID_NEXTMEETING_TIME_BOB, VALID_NEXTMEETING_TIME_BOB,
                    VALID_NEXTMEETING_LOCATION_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }

}
