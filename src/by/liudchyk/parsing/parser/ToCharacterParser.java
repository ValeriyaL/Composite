package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.Symbol;
import by.liudchyk.parsing.entity.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToCharacterParser extends AbstractParser {
    private Pattern pattern = Pattern.compile(SYMBOL_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        TextComposite wordComposite = new TextComposite(TextType.WORD);
        Matcher matcher = pattern.matcher(text);
        String symbol;
        while (matcher.find()) {
            symbol = matcher.group();
            Symbol leaf = new Symbol(symbol.charAt(0));
            wordComposite.add(leaf);
        }
        composite.add(wordComposite);
        return composite;
    }
}
