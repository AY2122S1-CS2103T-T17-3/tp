package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREATEDAT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREATEDAT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CURRENTPLAN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CURRENTPLAN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISPOSABLEINCOME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISPOSABLEINCOME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTMET_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTMET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKAPPETITE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKAPPETITE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {
    private static final String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    public static final Client ALICE = new ClientBuilder().withClientId("0").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRiskAppetite("2").withDisposableIncome("200")
            .withCurrentPlan("Prudential PRUShield").withLastMet("07-10-2021")
            .withBirthday("20-12-2000").withCreatedAt(dateToday)
            .withTags("friends").build();
    public static final Client BENSON = new ClientBuilder().withClientId("1").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
            .withRiskAppetite("4").withDisposableIncome("300").withCurrentPlan("Prudential PROLife")
            .withLastMet("08-10-2021").withNextMeeting("24-09-2021 (10:00~12:00), Starbucks @ UTown")
            .withBirthday("21-12-2000").withCreatedAt(dateToday)
            .withTags("owesMoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withClientId("2").withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRiskAppetite("3")
            .withDisposableIncome("100").withCurrentPlan("Aviva Shield").withLastMet("06-10-2021")
            .withBirthday("22-12-2000").withCreatedAt(dateToday)
            .withNextMeeting("24-09-2021 (10:00~12:00), Starbucks @ UTown").build();
    public static final Client DANIEL = new ClientBuilder().withClientId("3").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street").withRiskAppetite("1")
            .withDisposableIncome("50000").withCurrentPlan("Aviva Ace").withLastMet("02-10-2021")
            .withBirthday("23-12-2000").withCreatedAt(dateToday)
            .withNextMeeting("24-09-2021 (10:00~12:00), Starbucks @ UTown").withTags("friends").build();
    public static final Client ELLE = new ClientBuilder().withClientId("4").withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withRiskAppetite("1")
            .withDisposableIncome("4000").withCurrentPlan("AIA Gold")
            .withBirthday("24-12-2000").withCreatedAt(dateToday)
            .withLastMet("03-10-2021").build();
    public static final Client FIONA = new ClientBuilder().withClientId("5").withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withRiskAppetite("3")
            .withDisposableIncome("200").withCurrentPlan("Prudential PRUShield").withLastMet("04-10-2021")
            .withBirthday("25-12-2000").withCreatedAt(dateToday).build();
    public static final Client GEORGE = new ClientBuilder().withClientId("6").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street").withRiskAppetite("5")
            .withDisposableIncome("1").withCurrentPlan("AIA Zero").withLastMet("03-10-2021").withCreatedAt(dateToday)
            .build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withClientId("7").withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRiskAppetite("3")
            .withDisposableIncome("300").withCurrentPlan("Prudential PRUShield").withLastMet("10-06-2021")
            .withCreatedAt(dateToday).build();
    public static final Client IDA = new ClientBuilder().withClientId("8").withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRiskAppetite("4")
            .withDisposableIncome("300").withCurrentPlan("Prudential PRUShield").withLastMet("10-06-2021")
            .withCreatedAt(dateToday).build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withClientId("9").withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY)
            .withRiskAppetite(VALID_RISKAPPETITE_AMY).withDisposableIncome(VALID_DISPOSABLEINCOME_AMY)
            .withCurrentPlan(VALID_CURRENTPLAN_AMY).withLastMet(VALID_LASTMET_AMY).withCreatedAt(dateToday)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withClientId("10").withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withBirthday(VALID_BIRTHDAY_BOB)
            .withRiskAppetite(VALID_RISKAPPETITE_BOB).withDisposableIncome(VALID_DISPOSABLEINCOME_BOB)
            .withCurrentPlan(VALID_CURRENTPLAN_BOB).withLastMet(VALID_LASTMET_BOB).withCreatedAt(dateToday)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
