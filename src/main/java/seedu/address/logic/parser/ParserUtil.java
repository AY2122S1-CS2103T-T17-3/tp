package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.isEqualIgnoreCase;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_CONFIRMATION_FAIL;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.CurrentPlan;
import seedu.address.model.client.DisposableIncome;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastMet;
import seedu.address.model.client.Name;
import seedu.address.model.client.NextMeeting;
import seedu.address.model.client.Phone;
import seedu.address.model.client.RiskAppetite;
import seedu.address.model.client.SortDirection;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_CLIENT_ID = "Client ID is not a non-negative unsigned integer.";

    /**
     * Parses {@code clientId} into an {@code ClientId} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-negative integer).
     */
    public static ClientId parseClientId(String clientId) throws ParseException {
        String trimmedId = clientId.trim();
        if (!StringUtil.isNonNegativeInteger(trimmedId)) {
            throw new ParseException(MESSAGE_INVALID_CLIENT_ID);
        }
        return new ClientId(trimmedId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String birthday} into an {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!StringUtil.isValidDate(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String current plan} into an {@code CurrentPlan}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code currentPlan} is invalid.
     */
    public static CurrentPlan parseCurrentPlan(String currentPlan) throws ParseException {
        requireNonNull(currentPlan);
        String trimmedCurrentPlan = currentPlan.trim();
        if (!CurrentPlan.isValidCurrentPlan(trimmedCurrentPlan)) {
            throw new ParseException(CurrentPlan.MESSAGE_CONSTRAINTS);
        }
        return new CurrentPlan(trimmedCurrentPlan);
    }

    /**
     * Parses a {@code String LastMet} into an {@code LastMet}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code LastMet} is invalid.
     */
    public static LastMet parseLastMet(String lastMet) throws ParseException {
        requireNonNull(lastMet);
        String trimmedLastMet = lastMet.trim();
        if (!StringUtil.isValidDate(trimmedLastMet)) {
            throw new ParseException(LastMet.MESSAGE_CONSTRAINTS);
        }
        return new LastMet(trimmedLastMet);
    }

    /**
     * Parses a given String {@code String nextMeeting} to return a {@code NextMeeting}
     * Parses a {@code String RiskAppetite} into an {@code RiskAppetite}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nextMeeting} is invalid.
     */
    public static NextMeeting parseNextMeeting(String nextMeeting) throws ParseException {
        requireNonNull(nextMeeting);
        String trimmedNextMeeting = nextMeeting.trim();
        if (isEqualIgnoreCase(trimmedNextMeeting, NextMeeting.NO_NEXT_MEETING) || trimmedNextMeeting.isEmpty()) {
            return NextMeeting.NULL_MEETING;
        }

        if (!NextMeeting.isValidNextMeeting(trimmedNextMeeting)) {
            throw new ParseException(NextMeeting.MESSAGE_INVALID_MEETING_STRING);
        }

        String date = trimmedNextMeeting.split(" ", 2)[0];
        String startTime = trimmedNextMeeting.substring(trimmedNextMeeting.indexOf("(") + 1,
            trimmedNextMeeting.indexOf("~"));
        String endTime = trimmedNextMeeting.substring(trimmedNextMeeting.indexOf("~") + 1,
            trimmedNextMeeting.indexOf(")"));
        String location = trimmedNextMeeting.split(",", 2)[1].trim();
        return new NextMeeting(date, startTime, endTime, location, "");
    }

    /**
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code RiskAppetite} is invalid.
     */
    public static RiskAppetite parseRiskAppetite(String riskAppetite) throws ParseException {
        requireNonNull(riskAppetite);
        String trimmedRiskAppetite = riskAppetite.trim();
        if (!RiskAppetite.isValidRiskAppetite(trimmedRiskAppetite)) {
            throw new ParseException(RiskAppetite.MESSAGE_CONSTRAINTS);
        }
        return new RiskAppetite(trimmedRiskAppetite);
    }

    /**
     * Parses a {@code String disposableIncome} into an {@code DisposableIncome}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code DisposableIncome} is invalid.
     */
    public static DisposableIncome parseDisposableIncome(String disposableIncome) throws ParseException {
        requireNonNull(disposableIncome);
        String trimmedDisposableIncome = disposableIncome.trim();
        if (!DisposableIncome.isValidDisposableIncome(trimmedDisposableIncome)) {
            throw new ParseException(DisposableIncome.MESSAGE_CONSTRAINTS);
        }
        return new DisposableIncome(trimmedDisposableIncome);
    }

    /**
     * Parses a {@code String sortDirection} into an {@code SortDirection}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code DisposableIncome} is invalid.
     */
    public static SortDirection parseSortDirection(String sortDirection) throws ParseException {
        requireNonNull(sortDirection);
        String trimmedSortDirection = sortDirection.trim().toLowerCase();
        if (!SortDirection.isValidDirection(trimmedSortDirection)) {
            throw new ParseException(SortDirection.MESSAGE_CONSTRAINTS);
        }
        return SortDirection.of(trimmedSortDirection);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag, Model model) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();

        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        if (model.hasTagName(tag)) {
            return model.getTag(tag);
        } else {
            Tag newTag = new Tag(trimmedTag);
            model.addTag(newTag);
            return newTag;
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags, Model model) throws ParseException {
        requireNonNull(tags);

        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName, model));
        }

        return tagSet;
    }

    /**
     * Parses {@code input} to true for yes and false for no, {@code ParseException} is thrown otherwise.
     * {@code input} will be trimmed and converted to lower case before checking;
     */
    public static boolean parseConfirmation(String input) throws ParseException {
        String formattedInput = input.trim().toLowerCase();
        switch (formattedInput) {
        case "yes":
            return true;

        case "no":
            return false;

        default:
            throw new ParseException(MESSAGE_CONFIRMATION_FAIL);
        }
    }
}
