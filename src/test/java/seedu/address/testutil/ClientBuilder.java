package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.CreatedAt;
import seedu.address.model.client.CurrentPlan;
import seedu.address.model.client.DisposableIncome;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastMet;
import seedu.address.model.client.Name;
import seedu.address.model.client.NextMeeting;
import seedu.address.model.client.Phone;
import seedu.address.model.client.RiskAppetite;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_CLIENTID = "0";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "01-01-1990";
    public static final String DEFAULT_RISKAPPETITE = "3";
    public static final String DEFAULT_DISPOSABLEINCOME = "300";
    public static final String DEFAULT_LASTMET = "24-09-2021";
    public static final String DEFAULT_NEXTMEETING = "No meeting planned";
    public static final String DEFAULT_CREATEDAT = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    public static final String DEFAULT_CURRENTPLAN = "Prudential PRUwealth";

    private ClientId clientId;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthday birthday;
    private LastMet lastMet;
    private NextMeeting nextMeeting;
    private CreatedAt createdAt;
    private CurrentPlan currentPlan;
    private RiskAppetite riskAppetite;
    private DisposableIncome disposableIncome;
    private Set<Tag> tags;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        clientId = new ClientId(DEFAULT_CLIENTID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        riskAppetite = new RiskAppetite(DEFAULT_RISKAPPETITE);
        disposableIncome = new DisposableIncome(DEFAULT_DISPOSABLEINCOME);
        lastMet = new LastMet(DEFAULT_LASTMET);
        try {
            nextMeeting = ParserUtil.parseNextMeeting(DEFAULT_NEXTMEETING);
        } catch (ParseException pe) {
            nextMeeting = new NextMeeting("24-09-2021", "10:00", "12:00",
                "Starbucks @ UTown", name.fullName);
        }
        createdAt = new CreatedAt(DEFAULT_CREATEDAT);
        currentPlan = new CurrentPlan(DEFAULT_CURRENTPLAN);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        clientId = clientToCopy.getClientId();
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        riskAppetite = clientToCopy.getRiskAppetite();
        disposableIncome = clientToCopy.getDisposableIncome();
        currentPlan = clientToCopy.getCurrentPlan();
        lastMet = clientToCopy.getLastMet();
        nextMeeting = clientToCopy.getNextMeeting();
        createdAt = clientToCopy.getCreatedAt();
        tags = new HashSet<>(clientToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withClientId(String clientId) {
        this.clientId = new ClientId(clientId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Client} that we are building.
     */
    public ClientBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code RiskAppetite} of the {@code Client} that we are building.
     */
    public ClientBuilder withRiskAppetite(String riskAppetite) {
        this.riskAppetite = new RiskAppetite(riskAppetite);
        return this;
    }

    /**
     * Sets the {@code DisposableIncome} of the {@code Client} that we are building.
     */
    public ClientBuilder withDisposableIncome(String disposableIncome) {
        this.disposableIncome = new DisposableIncome(disposableIncome);
        return this;
    }

    /**
     * Sets the {@code LastMet} of the {@code Client} that we are building.
     */
    public ClientBuilder withLastMet(String lastMetDate) {
        this.lastMet = new LastMet(lastMetDate);
        return this;
    }

    /**
     * Sets the {@code NextMeeting} of the {@code Client} that we are building.
     */
    public ClientBuilder withNextMeeting(String nextMeeting) {
        try {
            this.nextMeeting = ParserUtil.parseNextMeeting(nextMeeting);
        } catch (ParseException pe) {
            this.nextMeeting = new NextMeeting("24-09-2021", "10:00", "12:00",
                "Starbucks @ UTown", null);
        }
        return this;
    }

    /**
     * Sets the {@code CreatedAt} of the {@code Client} that we are building.
     */
    public ClientBuilder withCreatedAt(String createdAt) {
        this.createdAt = new CreatedAt(createdAt);
        return this;
    }

    /**
     * Sets the {@code CurrentPlan} of the {@code Client} that we are building.
     */
    public ClientBuilder withCurrentPlan(String currentPlan) {
        this.currentPlan = new CurrentPlan(currentPlan);
        return this;
    }

    /**
     * @return {@code Client} that we are building
     */
    public Client build() {
        return new Client(clientId, name, phone, email, address, birthday, riskAppetite,
            disposableIncome, currentPlan, lastMet, nextMeeting, createdAt, tags);
    }

    /**
     * @return {@code Client} function that we are building
     */
    public Function<ClientId, Client> buildFunction() {
        return clientId -> new Client(clientId, name, phone, email, address, birthday,
            riskAppetite, disposableIncome, currentPlan, lastMet, nextMeeting, createdAt, tags);
    }
}
