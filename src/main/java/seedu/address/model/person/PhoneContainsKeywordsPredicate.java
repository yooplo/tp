package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests if a {@code Person}'s {@code Phone} number matches any of the keywords provided.
 */
public class PhoneContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    /**
     * Constructs a {@code PhoneContainsKeywordsPredicate} with the specified keywords.
     * Each keyword is converted to lowercase to facilitate case-insensitive matching.
     *
     * @param keywords List of keywords to match against a person's phone number.
     */
    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        // Normalizing keywords to lower case to optimize searches
        super(keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isEmpty())
                .map(String::toLowerCase)
                .toList());
    }

    @Override
    public boolean test(Person person) {
        if (person == null || person.getPhone() == null) {
            return false;
        }

        String phoneNumber = person.getPhone().value.toLowerCase();
        return super.getKeywords().stream()
                .anyMatch(phoneNumber::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Check if the same instance
        }

        // Check for type compatibility
        if (!(other instanceof PhoneContainsKeywordsPredicate)) {
            return false;
        }

        PhoneContainsKeywordsPredicate otherPredicate = (PhoneContainsKeywordsPredicate) other;
        return this.getKeywords().equals(otherPredicate.getKeywords());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", this.getKeywords()).toString();
    }
}
