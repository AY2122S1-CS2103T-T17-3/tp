package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.mapper.PrefixMapper.compareFunction;
import static seedu.address.commons.mapper.PrefixMapper.getName;

import java.util.Comparator;
import java.util.function.BiFunction;

import seedu.address.logic.parser.Prefix;

/**
 * Compare two {@code Client} based on {@code Prefix} and {@code SortDirection}
 * to determine their ordering
 */
public class SortByAttribute implements Comparator<Client> {

    private final SortDirection direction;
    private final Prefix prefix;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
    public SortByAttribute(Prefix prefix, SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
        this.prefix = prefix;
    }

    public SortByAttribute(Prefix prefix) {
        this(prefix, SortDirection.SORT_ASCENDING);
    }

    public String getPrefixName() {
        return getName(prefix);
    }

    public Comparator<Client> thenCompareByAttribute(Prefix prefix) {
        return this.thenComparing(new SortByAttribute(prefix, this.direction));
    }

    @Override
    public int compare(Client a, Client b) {
        BiFunction<Client, Client, Integer> compareFunction = compareFunction(prefix, direction);
        return compareFunction.apply(a, b);
    }
}
