package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextType;
import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToSentenceParser extends AbstractParser {
    private ToLexemeParser next = new ToLexemeParser();
    private Pattern sentPattern = Pattern.compile(SENTENCE_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher sentMatcher = sentPattern.matcher(text);
        TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
        String textPart;
        while (sentMatcher.find()) {
            textPart = sentMatcher.group();
            next.parse(textPart, paragraphComposite);
        }
        composite.add(paragraphComposite);
        return composite;
    }
}
