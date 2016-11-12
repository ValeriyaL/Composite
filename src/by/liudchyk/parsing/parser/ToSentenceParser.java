package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.ElementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToSentenceParser extends AbstractParser {
    private final static Logger LOG = LogManager.getLogger();
    private ToLexemeParser next = new ToLexemeParser();
    Pattern sentPattern = Pattern.compile(SENTENCE_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher sentMatcher = sentPattern.matcher(text);
        TextComposite sentenseComposite = new TextComposite(ElementType.SENTENCE);
        LOG.info("Sentence composite was created");
        String textPart;
        while (sentMatcher.find()) {
            textPart = sentMatcher.group();
            next.parse(textPart,sentenseComposite);
        }
        composite.add(sentenseComposite);
        return composite;
    }
}
