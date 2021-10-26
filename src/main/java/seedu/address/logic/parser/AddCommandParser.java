package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTMEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;
import static seedu.address.logic.parser.CliSyntax.allPrefixesPresent;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
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
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final Prefix[] REQUIRED_PREFIXES = {
        PREFIX_NAME, PREFIX_EMAIL
    };

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     * @return
     */
    @Override
    public AddCommand parse(String args, Model model) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, allPrefixLess(PREFIX_CLIENTID));
        if (!allPrefixesPresent(argMultimap, REQUIRED_PREFIXES)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        CreatedAt createdAt = new CreatedAt(LocalDate.now());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(Phone.DEFAULT_VALUE));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(Address.DEFAULT_VALUE));
        Birthday birthday = ParserUtil.parseBirthday(argMultimap
            .getValue(PREFIX_BIRTHDAY).orElse(Birthday.DEFAULT_VALUE));
        RiskAppetite riskAppetite = ParserUtil.parseRiskAppetite(argMultimap
            .getValue(PREFIX_RISKAPPETITE).orElse(RiskAppetite.DEFAULT_VALUE));
        DisposableIncome disposableIncome = ParserUtil.parseDisposableIncome(argMultimap
            .getValue(PREFIX_DISPOSABLEINCOME).orElse(DisposableIncome.DEFAULT_VALUE));
        LastMet lastMet = ParserUtil.parseLastMet(argMultimap.getValue(PREFIX_LASTMET).orElse(LastMet.DEFAULT_VALUE));
        CurrentPlan currentPlan = ParserUtil.parseCurrentPlan(argMultimap.getValue(PREFIX_CURRENTPLAN)
            .orElse(CurrentPlan.DEFAULT_VALUE));
        NextMeeting nextMeeting = ParserUtil.parseNextMeeting(argMultimap.getValue(PREFIX_NEXTMEETING)
            .orElse(NextMeeting.NO_NEXT_MEETING));
        nextMeeting.setWithWho(name);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG), model);

        Function<ClientId, Client> client = clientId -> new Client(clientId, name, phone, email, address, birthday,
            riskAppetite, disposableIncome, currentPlan, lastMet, nextMeeting, createdAt, tagList);

        return new AddCommand(client);
    }
}
