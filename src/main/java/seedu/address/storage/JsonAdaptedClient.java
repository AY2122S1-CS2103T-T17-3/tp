package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ParserUtil;
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

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String clientId;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String riskAppetite;
    private final String disposableIncome;
    private final String lastMet;
    private final String nextMeeting;
    private final String currentPlan;
    private final String createdAt;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("clientId") String clientId, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address, @JsonProperty("birthday") String birthday,
                             @JsonProperty("riskAppetite") String riskAppetite,
                             @JsonProperty("disposableIncome") String disposableIncome,
                             @JsonProperty("current-plan") String currentPlan,
                             @JsonProperty("last-met") String lastMet,
                             @JsonProperty("next-meeting") String nextMeeting,
                             @JsonProperty("created-at") String createdAt,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        this.clientId = clientId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.riskAppetite = riskAppetite;
        this.disposableIncome = disposableIncome;
        this.lastMet = lastMet;
        this.nextMeeting = nextMeeting;
        this.currentPlan = currentPlan;
        this.createdAt = createdAt;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        clientId = source.getClientId().value;
        name = source.getName().fullName;
        email = source.getEmail().value;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().dateInString;
        disposableIncome = source.getDisposableIncome().value;
        riskAppetite = source.getRiskAppetite().value;
        currentPlan = source.getCurrentPlan().value;
        lastMet = source.getLastMet().dateInString;
        nextMeeting = source.getNextMeeting().toString();
        createdAt = source.getCreatedAt().dateInString;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (clientId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ClientId.class.getSimpleName()));
        }

        final ClientId modelClientId = new ClientId(clientId);

        if (createdAt == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CreatedAt.class.getSimpleName()));
        }
        if (!StringUtil.isValidDate(createdAt)) {
            throw new IllegalValueException(CreatedAt.MESSAGE_CONSTRAINTS);
        }
        final CreatedAt modelCreatedAt = new CreatedAt(createdAt);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Birthday.class.getSimpleName()));
        }
        if (!StringUtil.isValidDate(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (lastMet == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LastMet.class.getSimpleName()));
        }
        if (!StringUtil.isValidDate(lastMet)) {
            throw new IllegalValueException(LastMet.MESSAGE_CONSTRAINTS);
        }
        final LastMet modelLastMet = new LastMet(lastMet);

        if (nextMeeting == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NextMeeting.class.getSimpleName()));
        }
        final NextMeeting modelNextMeeting = ParserUtil.parseNextMeeting(nextMeeting);
        modelNextMeeting.setWithWho(modelName);

        if (currentPlan == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CurrentPlan.class.getSimpleName()));
        }
        final CurrentPlan modelCurrentPlan = new CurrentPlan(currentPlan);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }

        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }

        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress = new Address(address);

        if (riskAppetite == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RiskAppetite.class.getSimpleName()));
        }

        if (!RiskAppetite.isValidRiskAppetite(riskAppetite)) {
            throw new IllegalValueException(RiskAppetite.MESSAGE_CONSTRAINTS);
        }

        final RiskAppetite modelRiskAppetite = new RiskAppetite(riskAppetite);

        if (disposableIncome == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DisposableIncome.class.getSimpleName()));
        }

        if (!DisposableIncome.isValidDisposableIncome(disposableIncome)) {
            throw new IllegalValueException(DisposableIncome.MESSAGE_CONSTRAINTS);
        }

        final DisposableIncome modelDisposableIncome = new DisposableIncome(disposableIncome);

        final Set<Tag> modelTags = new HashSet<>(clientTags);
        return new Client(modelClientId, modelName, modelPhone, modelEmail, modelAddress, modelBirthday,
                modelRiskAppetite, modelDisposableIncome, modelCurrentPlan, modelLastMet, modelNextMeeting,
                modelCreatedAt, modelTags);
    }

}
