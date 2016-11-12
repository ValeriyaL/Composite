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

public class ToParagraphParser extends AbstractParser {
    private final static Logger LOG = LogManager.getLogger();
    private ToSentenceParser next = new ToSentenceParser();
    Pattern parPattern = Pattern.compile(PARAGRAPH_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher parMatcher = parPattern.matcher(text);
        TextComposite paragraphComposite = new TextComposite(ElementType.PARAGRAPH);
        LOG.info("Paragraph composite created");
        String textPart;
        while (parMatcher.find()) {
            textPart = parMatcher.group();
            next.parse(textPart,paragraphComposite);
        }
        composite.add(paragraphComposite);
        return composite;
    }
}
