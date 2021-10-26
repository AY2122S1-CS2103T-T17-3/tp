package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.mapper.PrefixMapper.parseAndEditSet;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREATEDAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientId;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     * @return
     */
    @Override
    public EditCommand parse(String args, Model model) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        List<ClientId> clientIds = new ArrayList<>();
        try {
            String[] clientIdInput = argMultimap.getPreamble().split(" ");
            for (String s : clientIdInput) {
                clientIds.add(ParserUtil.parseClientId(s));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_CLIENTID).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_CHANGE_CLIENTID);
        }

        if (argMultimap.getValue(PREFIX_CREATEDAT).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_CHANGE_CREATEDAT);
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();

        Prefix[] prefixes = allPrefixLess(PREFIX_CLIENTID, PREFIX_CREATEDAT, PREFIX_TAG);
        for (Prefix prefix : prefixes) {
            if (argMultimap.getValue(prefix).isPresent()) {
                BiConsumer<EditClientDescriptor, String> parseEditSetFunction = parseAndEditSet(prefix);
                String toParse = argMultimap.getValue(prefix).get();
                parseEditSetFunction.accept(editClientDescriptor, toParse);
            }
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG), model).ifPresent(editClientDescriptor::setTags);

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(clientIds, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags, Model model) throws ParseException {
        requireNonNull(tags);

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet, model));
    }

}
