package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AbstractFindCommand;
import seedu.address.logic.commands.FindByEmailCommand;
import seedu.address.logic.commands.FindByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<AbstractFindCommand> {

    public static final Pattern KEYWORD_EXTRACTOR =
            Pattern.compile("^(?<tag>/[cen])");

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbstractFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        int index = trimmedArgs.indexOf(' ');
        String tag;
        String searchTerms = "";
        // Check if there is a whitespace character in the string
        if (index != -1) {
            tag = trimmedArgs.substring(0, index); // Part before the first whitespace
            searchTerms = trimmedArgs.substring(index + 1); // Part after the first whitespace
        } else {
            tag = trimmedArgs;
        }

        Matcher m = KEYWORD_EXTRACTOR.matcher(tag); // find tag and search words

        // will throw exception if no args/command format not correct
        if (args.isEmpty() || !m.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbstractFindCommand.MESSAGE_USAGE));
        }

        // extract tag and search argument
        String[] searchTermArray = searchTerms.split("\\s+");

        // return appropriate FindCommand class depending on tag
        switch (tag) {
        case "/n":
            if (searchTerms.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNameCommand.MESSAGE_USAGE));
            }
            return new FindByNameCommand(
                    new NameContainsKeywordsPredicate(Arrays.asList(searchTermArray)));
        case "/e":
            if (searchTerms.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByEmailCommand.MESSAGE_USAGE));
            }
            return new FindByEmailCommand(
                    new EmailContainsKeywordsPredicate(Arrays.asList(searchTermArray)));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbstractFindCommand.MESSAGE_USAGE));
        }
    }
}
