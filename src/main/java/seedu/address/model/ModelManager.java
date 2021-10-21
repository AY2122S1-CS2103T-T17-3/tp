package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final SortedList<Client> sortedClients;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Client> clientToView;
    private final FilteredList<Tag> filteredTags;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        sortedClients = new SortedList<>(this.addressBook.getClientList());
        filteredClients = new FilteredList<>(sortedClients);
        filteredTags = new FilteredList<>(this.addressBook.getTagList());
        clientToView = new FilteredList<>(this.addressBook.getClientList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public boolean hasClientId(ClientId clientId) {
        requireNonNull(clientId);
        return addressBook.hasClientId(clientId);
    }

    @Override
    public List<Client> deleteClientByClientIds(List<ClientId> clientIds) {
        return addressBook.deleteClientByClientIds(clientIds);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public List<Client> setClientByClientIds(List<ClientId> clientIds, EditClientDescriptor editedClientDescriptor) {
        requireAllNonNull(clientIds, editedClientDescriptor);

        return addressBook.setClientByClientIds(clientIds, editedClientDescriptor);
    }

    @Override
    public Client getClient(ClientId clientId) {
        requireNonNull(clientId);
        return addressBook.getClient(clientId);
    }

    @Override
    public boolean hasTagName(String tagName) {
        requireNonNull(tagName);
        return addressBook.hasTagName(tagName);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public Tag getTag(String tagName) {
        requireNonNull(tagName);
        return addressBook.getTag(tagName);
    }

    // TODO: divider here
    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public void filterFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        Predicate<? super Client> currentPredicate = filteredClients.getPredicate();
        if (currentPredicate == null) {
            currentPredicate = PREDICATE_SHOW_ALL_CLIENTS;
        }
        filteredClients.setPredicate(predicate.and(currentPredicate));
    }

    @Override
    public void sortFilteredClientList(Comparator<Client> sorter) {
        sortedClients.setComparator(sorter);
    }

    //=========== Client To View List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getClientToView() {
        return clientToView;
    }

    @Override
    public boolean isClientExistToView() {
        return clientToView.size() == 1;
    }

    @Override
    public String getNameOfClientToView() {
        return clientToView.get(0).getName().toString();
    }

    @Override
    public void updateClientToView(Predicate<Client> predicate) {
        requireNonNull(predicate);
        clientToView.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredTags.equals(other.filteredTags)
                && clientToView.equals(other.clientToView);
    }
}
