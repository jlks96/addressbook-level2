package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] addressFields = trimmedAddress.split("\\s*,\\s*");
        block = new Block(addressFields[0].trim());
        street = new Street(addressFields[1].trim());
        unit = new Unit(addressFields[2].trim());
        postalCode = new PostalCode(addressFields[3].trim());

    }

    /**
     * Returns full address as a string.
     */
    public String getFullAddress() {
        return (block.getBlockNumber() + ", "
                + street.getStreet() + ", "
                + unit.getUnitNumber() + ", "
                + postalCode.getPostalCode());
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return getFullAddress();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.getFullAddress().equals(((Address) other).getFullAddress())); // state check
    }

    @Override
    public int hashCode() {
        return getFullAddress().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
