package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new ClientId("0"), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"), new DisposableIncome("500"),
                new CurrentPlan(
                    "Prudential PRUwealth, AIA Pro Achiever 2.0, Syfe Select, a lot more plans......."
                        + ", Even more plans................................."),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "Alex Yeoh"),
                new CreatedAt("01-01-2021"),
                getTagSet("friends")),
            new Client(new ClientId("1"), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"), new DisposableIncome("500"), new CurrentPlan("Prudential PRUwealth"),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "Bernice Yu"),
                new CreatedAt("01-01-2021"), getTagSet("colleagues", "friends")),
            new Client(new ClientId("2"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"), new DisposableIncome("500"), new CurrentPlan("Prudential PRUwealth"),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "Charlotte Oliveiro"),
                new CreatedAt("01-01-2021"), getTagSet("neighbours")),
            new Client(new ClientId("3"), new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"), new DisposableIncome("500"), new CurrentPlan("Prudential PRUwealth"),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "David Li"),
                new CreatedAt("01-01-2021"), getTagSet("family")),
            new Client(new ClientId("4"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"), new DisposableIncome("500"), new CurrentPlan("Prudential PRUwealth"),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "Irfan Ibrahim"),
                new CreatedAt("01-01-2021"), getTagSet("classmates")),
            new Client(new ClientId("5"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Birthday("01-01-1990"),
                new RiskAppetite("1"),
                new DisposableIncome("500"), new CurrentPlan("Prudential PRUwealth"),
                new LastMet("24-01-2021"),
                new NextMeeting("25-12-2021", "08:00", "09:00", "Starbucks @ UTown", "Roy Balakrishnan"),
                new CreatedAt("01-01-2021"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
            if (sampleClient.hasNextMeeting()) {
                sampleAb.addNextMeeting(sampleClient.getNextMeeting());
            }
        }
        sampleAb.setClientCounter("6");
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
