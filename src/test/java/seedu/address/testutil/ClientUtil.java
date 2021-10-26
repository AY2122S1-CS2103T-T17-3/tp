package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTMEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().value + " ");
        sb.append(PREFIX_BIRTHDAY + client.getBirthday().dateInString + " ");
        sb.append(PREFIX_RISKAPPETITE + client.getRiskAppetite().value + " ");
        sb.append(PREFIX_DISPOSABLEINCOME + client.getDisposableIncome().value + " ");
        sb.append(PREFIX_CURRENTPLAN + client.getCurrentPlan().value + " ");
        sb.append(PREFIX_LASTMET + client.getLastMet().dateInString + " ");
        sb.append(PREFIX_NEXTMEETING + client.getNextMeeting().toString() + " ");
        client.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.getName() + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getBirthday().ifPresent(birthday -> sb.append(PREFIX_BIRTHDAY)
                .append(birthday.dateInString).append(" "));
        descriptor.getRiskAppetite().ifPresent(riskAppetite -> sb.append(PREFIX_RISKAPPETITE)
                .append(riskAppetite.value).append(" "));
        descriptor.getDisposableIncome().ifPresent(disposableIncome -> sb.append(PREFIX_DISPOSABLEINCOME)
                .append(disposableIncome.value).append(" "));
        descriptor.getCurrentPlan().ifPresent(currentPlan -> sb.append(PREFIX_CURRENTPLAN)
                .append(currentPlan.value).append(" "));
        descriptor.getLastMet().ifPresent(lastMet -> sb.append(PREFIX_LASTMET)
                .append(lastMet.dateInString).append(" "));
        descriptor.getNextMeeting().ifPresent(nextMeeting -> sb.append(PREFIX_NEXTMEETING)
                .append(nextMeeting.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.getName()).append(" "));
            }
        }
        return sb.toString();
    }
}

