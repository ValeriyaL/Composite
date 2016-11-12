package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.Symbol;
import by.liudchyk.parsing.entity.ElementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToCharacterParser extends AbstractParser {
    private final static Logger LOG = LogManager.getLogger();
    private Pattern pattern = Pattern.compile(SYMBOL_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        TextComposite symbolComposite = new TextComposite(ElementType.SYMBOL);
        Matcher matcher = pattern.matcher(text);
        LOG.info("Symbol composite was created");
        String symbol;
        while (matcher.find()) {
            symbol = matcher.group();
            Symbol leaf = new Symbol(symbol.charAt(0));
            symbolComposite.add(leaf);
        }
        composite.add(symbolComposite);
        return composite;
    }
}
