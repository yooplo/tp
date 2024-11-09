package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    /**
     * Constructs a {@code TagContainsKeywordsPredicate} with the given list of keywords.
     * Each keyword is converted to lowercase to facilitate case-insensitive matching.
     *
     * @param keywords List of keywords to match against {@code Person}'s tags.
     */
    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean test(Person person) {
        return getKeywords().stream().anyMatch(keyword -> isKeywordPresentInPersonTags(keyword, person));
    }

    /**
     * Checks if a given keyword is present in a person's tags.
     * If the keyword is empty, it checks whether the person has no tags.
     *
     * @param keyword The keyword to search for in the person's tags.
     * @param person The person whose tags are to be checked.
     * @return True if the keyword is present in the person's tags
     *     or if the keyword is empty and the person has no tags.
     */
    private boolean isKeywordPresentInPersonTags(String keyword, Person person) {
        if (keyword.isEmpty()) {
            return !person.hasTag();
        }
        return person.getTags().stream()
                .map(tag -> tag.toString().toLowerCase())
                .anyMatch(tag -> tag.contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }
        TagContainsKeywordsPredicate that = (TagContainsKeywordsPredicate) other;
        return this.getKeywords().equals(that.getKeywords());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", getKeywords()).toString();
    }
}
