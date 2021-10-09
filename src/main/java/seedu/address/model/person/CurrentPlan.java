package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class CurrentPlan {
    public final String value;
    public static final String MESSAGE_CONSTRAINTS = "Current plan can take any values, and it can be blank";
    
    public static final boolean IS_BLANK_VALUE_ALLOWED = true;
    public static final String DEFAULT_VALUE = "";

    /*
     * The first character of the current plan must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Constructs an {@code CurrentPlan}.
     *
     * @param currentPlan valid current plan.
     */
    public CurrentPlan(String currentPlan) {
        requireNonNull(currentPlan);
        value = currentPlan;
    }

    /**
     * Returns true if a given string is a valid current plan.
     */
    public static boolean isValidCurrentPlan(String test) {
        return (IS_BLANK_VALUE_ALLOWED && test.isEmpty())
                || test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentPlan // instanceof handles nulls
                && value.equals(((CurrentPlan) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
