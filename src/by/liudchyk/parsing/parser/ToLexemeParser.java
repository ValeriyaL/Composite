package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToLexemeParser extends AbstractParser {
    private Pattern lexPattern = Pattern.compile(LEXEME_REGEX);
    private ToWordParser next = new ToWordParser();

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher lexMatcher = lexPattern.matcher(text);
        TextComposite sentenceComposite = new TextComposite(TextType.SENTENCE);
        String textPart;
        while (lexMatcher.find()) {
            textPart = lexMatcher.group();
            if (!textPart.isEmpty()) {
                next.parse(textPart, sentenceComposite);
            }
        }
        composite.add(sentenceComposite);
        return composite;
    }
}
