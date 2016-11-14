package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */

public class ToParagraphParser extends AbstractParser {
    private ToSentenceParser next = new ToSentenceParser();
    private Pattern parPattern = Pattern.compile(PARAGRAPH_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher parMatcher = parPattern.matcher(text);
        String textPart;
        while (parMatcher.find()) {
            textPart = parMatcher.group();
            textPart = textPart.trim();
            next.parse(textPart, composite);
        }
        return composite;
    }
}
