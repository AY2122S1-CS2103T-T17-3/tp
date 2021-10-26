package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isValidDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.StringUtil;

public class Birthday implements OptionalNonStringBasedField {
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be in the form of Day-Month-Year, "
            + "where Day, month and year should be numerical values.";

    public final LocalDate value;
    public final String dateInString;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthdayDate client's birthday
     */
    public Birthday(String birthdayDate) {
        if (!IS_NULL_VALUE_ALLOWED) {
            requireNonNull(birthdayDate);
        }
        if (birthdayDate == null) {
            birthdayDate = "";
        }

        checkArgument(isValidDate(birthdayDate), MESSAGE_CONSTRAINTS);
        dateInString = birthdayDate;

        if (birthdayDate.isEmpty()) {
            value = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            value = LocalDate.parse(birthdayDate, formatter);
        }
    }

    /**
     * Returns if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return StringUtil.isValidDate(test);
    }

    @Override
    public String toString() {
        if (value == null) {
            return DEFAULT_VALUE;
        } else {
            return this.dateInString;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }
}
