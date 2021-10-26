package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_CLIENTID = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_RISKAPPETITE = new Prefix("r/");
    public static final Prefix PREFIX_DISPOSABLEINCOME = new Prefix("d/");
    public static final Prefix PREFIX_LASTMET = new Prefix("l/");
    public static final Prefix PREFIX_NEXTMEETING = new Prefix("m/");
    public static final Prefix PREFIX_CURRENTPLAN = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CREATEDAT = new Prefix("z/");

    /* Array of all Prefix */
    public static final Prefix[] ALL_PREFIXES = {
        PREFIX_CLIENTID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_RISKAPPETITE,
        PREFIX_DISPOSABLEINCOME, PREFIX_LASTMET, PREFIX_NEXTMEETING, PREFIX_CURRENTPLAN, PREFIX_TAG, PREFIX_CREATEDAT
    };


    /**
     * Returns an array of all prefix less {@code prefixes}.
     *
     * @param prefixes prefix to be excluded
     */
    public static Prefix[] allPrefixLess(Prefix... prefixes) {
        List<Prefix> excludePrefix = List.of(prefixes);
        List<Prefix> resultPrefix = new ArrayList<>(Arrays.asList(ALL_PREFIXES));
        resultPrefix.removeAll(excludePrefix);
        return resultPrefix.toArray(new Prefix[] {});
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean allPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix[] prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Returns true if any of the prefixes does not contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
