package seedu.addressbook.data;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.tag.Tag;

/**
 * An association class which links a {@code Tag} and a {@code Person}
 */
public class Tagging {
    Person person;
    Tag tag;
    boolean toAdd;

    public Tagging(Person person, Tag tag, boolean toAdd) {
        this.person = person;
        this.tag = tag;
        this.toAdd = toAdd;
    }

    @Override
    public String toString() {
        if (toAdd) {
            return "+ " + person.getName() + " " + tag;
        } else {
            return "- " + person.getName() + " " + tag;
        }
    }
}
