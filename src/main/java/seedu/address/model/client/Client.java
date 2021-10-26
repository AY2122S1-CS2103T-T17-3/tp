package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final ClientId clientId;
    private final Name name;
    private final Email email;

    // Data fields
    private final Phone phone;
    private final Address address;
    private final Birthday birthday;
    private final RiskAppetite riskAppetite;
    private final DisposableIncome disposableIncome;
    private final CurrentPlan currentPlan;
    private final LastMet lastMet;
    private final NextMeeting nextMeeting;
    private final CreatedAt createdAt;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Client(ClientId clientId, Name name, Phone phone, Email email, Address address, Birthday birthday,
                  RiskAppetite riskAppetite, DisposableIncome disposableIncome, CurrentPlan currentPlan,
                  LastMet lastMet, NextMeeting nextMeeting, CreatedAt createdAt, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.clientId = clientId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.riskAppetite = riskAppetite;
        this.disposableIncome = disposableIncome;
        this.currentPlan = currentPlan;
        this.lastMet = lastMet;
        this.nextMeeting = nextMeeting;
        this.createdAt = createdAt;
        addTags(tags);
    }

    public void delete() {
        this.tags.forEach(t -> t.removeClient(this));
    }

    public ClientId getClientId() {
        return clientId;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public LastMet getLastMet() {
        return lastMet;
    }

    public NextMeeting getNextMeeting() {
        return nextMeeting;
    }

    public CurrentPlan getCurrentPlan() {
        return currentPlan;
    }

    public RiskAppetite getRiskAppetite() {
        return riskAppetite;
    }

    public DisposableIncome getDisposableIncome() {
        return disposableIncome;
    }

    public boolean hasNextMeeting() {
        return !nextMeeting.isNullMeeting();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Assigns {@code tags} to the {@code Client}.
     *
     * @param tags Tags to be added.
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
        this.tags.forEach(t -> t.addClient(this));
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == null) {
            return false;

        }

        if (otherClient == this) {
            return true;
        }

        return otherClient.getName().equals(getName())
                || otherClient.getEmail().equals(getEmail());
    }

    public LocalDate getNextMeetingDate() {
        return nextMeeting.getDate();
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getClientId().equals(getClientId())
                && otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getBirthday().equals(getBirthday())
                && otherClient.getRiskAppetite().equals(getRiskAppetite())
                && otherClient.getDisposableIncome().equals(getDisposableIncome())
                && otherClient.getCurrentPlan().equals(getCurrentPlan())
                && otherClient.getLastMet().equals(getLastMet())
                && otherClient.getNextMeeting().equals(getNextMeeting())
                && otherClient.getCreatedAt().equals(getCreatedAt())
                && otherClient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, createdAt, address, birthday, lastMet, currentPlan, riskAppetite,
            disposableIncome, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client ID: ")
                .append(getClientId())
                .append("; Name: ")
                .append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; CreatedAt: ")
                .append(getCreatedAt())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress())
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Risk Appetite: ")
                .append(getRiskAppetite())
                .append("; Disposable Income: ")
                .append(getDisposableIncome())
                .append("; current plans: ")
                .append(getCurrentPlan())
                .append("; Last Met: ")
                .append(getLastMet())
                .append("; Next Meeting: ")
                .append(getNextMeeting());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
