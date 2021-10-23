package seedu.address.ui;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.Logic;


/**
 * Menu containing the menu item of different Address Book to switch to.
 */
public class AddressBookListMenu extends UiPart<Menu> {
    private static final String FXML = "AddressBookListMenu.fxml";

    @FXML
    private Menu menu;

    private final ObservableList<MenuItem> menuItems;

    /**
     * Creates a {@code AddressBookListMenu} with the give {@code Logic}.
     * @param jsonFileList ObservableList containing the {@code Path} to all the Address Book Json file
     */
    public AddressBookListMenu(ObservableList<Path> jsonFileList, Logic logic) {
        super(FXML);
        this.menuItems = this.menu.getItems();
        Function<Path, MenuItem> menuItemFunction = path -> {
            String addressBookName;
            if (!FileUtil.isJsonFile(path)) {
                return null;
            }
            addressBookName = FileUtil.convertToAddressBookName(path);
            MenuItem temp = new MenuItem(addressBookName);
            temp.setOnAction(x -> logic.switchAddressBook(path));
            return temp;
        };

        Consumer<Path> addedConsumer = path ->
                Optional.ofNullable(menuItemFunction.apply(path)).ifPresent(menuItems::add);

        Consumer<Path> removedConsumer = path -> {
            Predicate<MenuItem> matchMenuItem = menuItem -> menuItem.getText().equals(path.toString());
            ObservableList<MenuItem> temp = menuItems.filtered(matchMenuItem);
            if (temp.isEmpty()) {
                return;
            }

            MenuItem tempMenu = temp.get(0);
            menuItems.remove(tempMenu);
        };

        jsonFileList.forEach(addedConsumer);
        ListChangeListener<Path> addressBookListListener = c -> {
            while (c.next()) {
                c.getAddedSubList().forEach(addedConsumer);
                c.getRemoved().forEach(removedConsumer);
            }
        };
        jsonFileList.addListener(addressBookListListener);
    }
}
