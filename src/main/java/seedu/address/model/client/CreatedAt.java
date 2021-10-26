package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isValidDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatedAt {
    public static final String MESSAGE_CONSTRAINTS = "CreatedAt should be a valid date.";

    public final LocalDate value;
    public final String dateInString;

    /**
     * Constructs an {@code CreatedAt} from the given {@code date}.
     *
     * @param date date of creation in LocalDate
     */
    public CreatedAt(LocalDate date) {
        requireNonNull(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dateInString = date.format(formatter);

        checkArgument(isValidDate(dateInString), MESSAGE_CONSTRAINTS);

        value = date;
    }

    /**
     * Constructs an {@code CreatedAt} from the given {@code dateString}.
     *
     * @param dateString date of creation in String
     */
    public CreatedAt(String dateString) {
        requireNonNull(dateString);

        checkArgument(!dateString.isEmpty() && isValidDate(dateString), MESSAGE_CONSTRAINTS);

        dateInString = dateString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        value = LocalDate.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return this.dateInString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreatedAt // instanceof handles nulls
                && value.equals(((CreatedAt) other).value)); // state check
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }
}
