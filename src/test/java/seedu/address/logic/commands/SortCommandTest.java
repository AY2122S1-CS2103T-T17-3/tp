package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.model.person.SortDirection.SORT_ASCENDING;
import static seedu.address.model.person.SortDirection.SORT_DESCENDING;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.SortByAttribute;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SortByAttribute sortByAddressAscending = new SortByAttribute(PREFIX_ADDRESS, SORT_ASCENDING);
        SortByAttribute sortByAddressDesceding = new SortByAttribute(PREFIX_ADDRESS, SORT_DESCENDING);
        SortByAttribute sortByClientIdAscending = new SortByAttribute(PREFIX_CLIENTID, SORT_ASCENDING);
        SortByAttribute sortByClientIdDescending = new SortByAttribute(PREFIX_CLIENTID, SORT_DESCENDING);

        SortCommand sortCommandAddressAsc = new SortCommand(sortByAddressAscending);
        SortCommand sortCommandAddressDsc = new SortCommand(sortByAddressDesceding);
        SortCommand sortCommandClientIdAsc = new SortCommand(sortByClientIdAscending);
        SortCommand sortCommandClientIdDsc = new SortCommand(sortByClientIdDescending);

        // same object -> returns true
        assertTrue(sortCommandAddressAsc.equals(sortCommandAddressAsc));

        // same values -> returns true
        SortCommand sortAddressSortCommandAsc = new SortCommand(sortByAddressAscending);
        assertTrue(sortCommandAddressAsc.equals(sortAddressSortCommandAsc));

        // different sort direction -> returns false
        assertFalse(sortCommandAddressAsc.equals(sortCommandAddressDsc));

        // different fields -> returns false
        assertFalse(sortCommandClientIdAsc.equals(sortCommandClientIdDsc));

        // null -> returns false
        assertFalse(sortCommandAddressAsc.equals(null));

    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToClientId() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Client Id");
        SortByAttribute sorter = new SortByAttribute(PREFIX_CLIENTID, SORT_DESCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToName() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Name");
        SortByAttribute sorter = new SortByAttribute(PREFIX_NAME, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToEmail() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Email");
        SortByAttribute sorter = new SortByAttribute(PREFIX_EMAIL, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE, DANIEL, CARL, BENSON, FIONA, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToAddress() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Address");
        SortByAttribute sorter = new SortByAttribute(PREFIX_ADDRESS, SORT_DESCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE, BENSON, ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToRiskAppetite() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortByAttribute sorter = new SortByAttribute(PREFIX_RISKAPPETITE, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, ALICE, CARL, FIONA, BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToDisposableIncome() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Disposable Income");
        SortByAttribute sorter = new SortByAttribute(PREFIX_DISPOSABLEINCOME, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, CARL, ALICE, FIONA, BENSON, ELLE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToCurrentPlan() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Current Plan");
        SortByAttribute sorter = new SortByAttribute(PREFIX_CURRENTPLAN, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE, DANIEL, CARL, BENSON, ALICE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToLastMet() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Last Met");
        SortByAttribute sorter = new SortByAttribute(PREFIX_LASTMET, SORT_ASCENDING);
        SortCommand command = new SortCommand(sorter);
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, GEORGE, FIONA, CARL, ALICE, BENSON), model.getFilteredPersonList());
    }

}
