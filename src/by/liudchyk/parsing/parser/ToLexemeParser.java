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
public class ToLexemeParser extends AbstractParser {
    private final static Logger LOG = LogManager.getLogger();
    Pattern lexPattern = Pattern.compile(LEXEME_REGEX);
    private ToWordParser next = new ToWordParser();

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        Matcher lexMatcher = lexPattern.matcher(text);
        TextComposite lexemeComposite = new TextComposite(ElementType.LEXEME);
        LOG.info("Lexeme composite was created");
        String textPart;
        while(lexMatcher.find()){
            textPart = lexMatcher.group();
            if(!textPart.isEmpty()) {
                next.parse(textPart, lexemeComposite);
            }
        }
        composite.add(lexemeComposite);
        return composite;
    }
}
