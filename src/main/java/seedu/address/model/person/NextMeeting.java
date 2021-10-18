package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.convertEmptyStringIfNull;
import static seedu.address.commons.util.StringUtil.isValidDate;
import static seedu.address.commons.util.StringUtil.isValidTime;
import static seedu.address.commons.util.StringUtil.parseToLocalDate;
import static seedu.address.commons.util.StringUtil.parseToLocalTime;

import java.time.LocalDate;
import java.time.LocalTime;

public class NextMeeting implements OptionalPersonNonStringField {

    public static final String DATE_MESSAGE_CONSTRAINTS = "Next meeting date should be in the form of Day-Month-Year, "
        + "where Day, month and year should be numerical values.";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Next meeting time should be in the 24-hour format, "
        + "where Hour and Minutes should be numerical values.";
    public static final String MESSAGE_INVALID_MEETING_STRING = "String representation of Next Meeting is not correct";
    public static final String NO_NEXT_MEETING = "No meeting planned.";
    public static final NextMeeting NULL_MEETING = new NextMeeting(null, null, null,
        null);

    public static final String VALID_MEETING_STRING =
        "([0-9]{2})-([0-9]{2})-([0-9]{4}) \\(([0-9]{2}):([0-9]{2})~([0-9]{2}):([0-9]{2})\\),(.|\\s)*\\S(.|\\s)*";

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;

    public final String dateInString;
    public final String startTimeInString;
    public final String endTimeInString;
    public final String location;

    /**
     * Constructs a {@code NextMeeting}.
     *
     * @param date date agent next meets a client
     */
    public NextMeeting(String date, String startTime, String endTime, String location) {
        if (!IS_NULL_VALUE_ALLOWED) {
            requireNonNull(date);
            requireNonNull(startTime);
            requireNonNull(endTime);
            requireNonNull(location);
        }

        date = convertEmptyStringIfNull(date);
        startTime = convertEmptyStringIfNull(startTime);
        endTime = convertEmptyStringIfNull(endTime);
        this.location = convertEmptyStringIfNull(location);

        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        dateInString = date;

        checkArgument(isValidTime(startTime), TIME_MESSAGE_CONSTRAINTS);
        startTimeInString = startTime;

        checkArgument(isValidTime(endTime), TIME_MESSAGE_CONSTRAINTS);
        endTimeInString = endTime;

        this.date = parseToLocalDate(date);
        this.startTime = parseToLocalTime(startTime);
        this.endTime = parseToLocalTime(endTime);
    }

    public static NextMeeting getNullMeeting() {
        return NULL_MEETING;
    }

    public static boolean isValidNextMeeting(String test) {
        return test.matches(VALID_MEETING_STRING);
    }

    @Override
    public String toString() {
        if (date == null) {
            return NO_NEXT_MEETING;
        }
        return String.format("%s (%s~%s), %s", dateInString, startTimeInString, endTimeInString, location);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NextMeeting // instanceof handles nulls
            && dateInString.equals(((NextMeeting) other).dateInString)
            && startTimeInString.equals(((NextMeeting) other).startTimeInString)
            && endTimeInString.equals(((NextMeeting) other).endTimeInString)
            && location.equals(((NextMeeting) other).location)); // state check
    }
}
