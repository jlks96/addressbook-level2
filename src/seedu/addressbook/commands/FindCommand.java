package seedu.addressbook.commands;

import java.util.*;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();

        keywords = getKeywordsInLowerCase(keywords);

        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            wordsInName = getWordsInNameInLowerCase(wordsInName);
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }


    /**
     * Converts words in name to lower case
     *
     * @param wordsInName contains words in name to be ccnverted
     * @return set of words in name in lower case
     */
    private static Set<String> getWordsInNameInLowerCase(Set<String> wordsInName) {
        Set<String> candidatesToRemove = new HashSet<>();
        Set<String> candidatesToAdd = new HashSet<>();

        for (String wordInName : wordsInName) {
            candidatesToRemove.add(wordInName);
            candidatesToAdd.add(wordInName.toLowerCase());
        }
        wordsInName.removeAll(candidatesToRemove);
        wordsInName.addAll(candidatesToAdd);
        return wordsInName;
    }

    /**
     * Converts keywords to lower case
     *
     * @param keywords contains keywords to be ccnverted
     * @return collection of keywords in lower case
     */
    private static Set<String> getKeywordsInLowerCase(Set<String> keywords) {
        Set<String> candidatesToRemove = new HashSet<>();
        Set<String> candidatesToAdd = new HashSet<>();

        for (String keyword : keywords) {
            candidatesToRemove.remove(keyword);
            candidatesToAdd.add(keyword.toLowerCase());
        }

        keywords.removeAll(candidatesToRemove);
        keywords.addAll(candidatesToAdd);
        return keywords;
    }

}
