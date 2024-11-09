package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests if a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    /**
     * Constructs an {@code EmailContainsKeywordsPredicate} with the specified keywords.
     * Each keyword is converted to lowercase to facilitate case-insensitive matching.
     *
     * @param keywords List of keywords to match against a person's email.
     */
    public EmailContainsKeywordsPredicate(List<String> keywords) {
        // Normalizing keywords to lower case to optimize search
        super(keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isEmpty())
                .map(String::toLowerCase)
                .toList());
    }

    @Override
    public boolean test(Person person) {
        if (person == null || person.getEmail() == null) {
            return false;
        }

        String email = person.getEmail().value.toLowerCase();
        return super.getKeywords().stream()
                .anyMatch(email::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EmailContainsKeywordsPredicate)) {
            return false;
        }

        EmailContainsKeywordsPredicate otherPredicate = (EmailContainsKeywordsPredicate) other;
        return this.getKeywords().equals(otherPredicate.getKeywords());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", this.getKeywords())
                .toString();
    }
}
