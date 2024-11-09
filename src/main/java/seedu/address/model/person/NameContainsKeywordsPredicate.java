package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests if a {@code Person}'s {@code Name} matches any of the keywords provided.
 */
public class NameContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    /**
     * Constructs a {@code NameContainsKeywordsPredicate} with the specified keywords.
     * Each keyword is converted to lowercase to facilitate case-insensitive matching.
     *
     * @param keywords List of keywords to match against a person's name.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        // Normalizing keywords to lower case to optimize searches
        super(keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isEmpty())
                .map(String::toLowerCase)
                .toList());
    }

    @Override
    public boolean test(Person person) {
        if (person == null || person.getName() == null) {
            return false;
        }

        String fullName = person.getName().fullName.toLowerCase();
        return super.getKeywords().stream()
                .anyMatch(fullName::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        // Check for type compatibility
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherPredicate = (NameContainsKeywordsPredicate) other;
        return this.getKeywords().equals(otherPredicate.getKeywords());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", this.getKeywords()).toString();
    }
}
