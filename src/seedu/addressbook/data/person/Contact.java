package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Contact {


    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Contact(String value, boolean isPrivate, String vadidationRegex, String messageConstraints) throws IllegalValueException {

        this.isPrivate = isPrivate;
        String trimmedValue = value.trim();
        if (!isValidValue(trimmedValue, vadidationRegex)) {
            throw new IllegalValueException(messageConstraints);
        }
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public boolean isValidValue(String test, String regex) {
        return test.matches(regex);
    }


    @Override
    public String toString() {
        return value;
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }



}
