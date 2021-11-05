package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Client.EditClientDescriptor;
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
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setEmail(client.getEmail());
        descriptor.setLastMet(client.getLastMet());
        descriptor.setCurrentPlan(client.getCurrentPlan());
        descriptor.setPhone(client.getPhone());
        descriptor.setAddress(client.getAddress());
        descriptor.setRiskAppetite(client.getRiskAppetite());
        descriptor.setDisposableIncome(client.getDisposableIncome());
        descriptor.setNextMeeting(client.getNextMeeting());
        descriptor.setTags(client.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Current Plan} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withCurrentPlan(String currentPlan) {
        descriptor.setCurrentPlan(new CurrentPlan(currentPlan));
        return this;
    }

    /**
     * Sets the {@code RiskAppetite} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withRiskAppetite(String riskAppetite) {
        descriptor.setRiskAppetite(new RiskAppetite(riskAppetite));
        return this;
    }

    /**
     * Sets the {@code LastMet} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withLastMet(String lastMetDate) {
        descriptor.setLastMet(new LastMet(lastMetDate));
        return this;
    }

    /**
     * Sets the {@code DisposableIncome} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withDisposableIncome(String disposableIncome) {
        descriptor.setDisposableIncome(new DisposableIncome(disposableIncome));
        return this;
    }

    /**
     * Sets the {@code NextMeeting} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withNextMeeting(String date, String startTime, String endTime, String location) {
        descriptor.setNextMeeting(new NextMeeting(date, startTime, endTime, location, null));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditClientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditClientDescriptor build() {
        return descriptor;
    }
}
