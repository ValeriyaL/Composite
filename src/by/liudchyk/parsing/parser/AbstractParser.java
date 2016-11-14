package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;

/**
 * Created by Admin on 03.11.2016.
 */

public abstract class AbstractParser {
    public static final String PARAGRAPH_REGEX = "(\\t|\\s)+.+(\r\n)?";
    public static final String SENTENCE_REGEX = "([0-9]|[A-Z]|\\(\\-|\\+\\+|\\-\\-)[^.!?]*[\\.!\\?]";
    public static final String LEXEME_REGEX = "(([0-9\\+\\-\\*/]|\\(|\\)|\\+\\+|--)+)|('?([A-z]|\\-)+)[,:;.!?']?";
    public static final String WORD_REGEX = "(([A-z]|\\-)+[A-z])|([A-z])";
    public static final String EXPRESSION_REGEX = "([0-9\\+\\-\\*/]|\\(|\\)|\\+\\+|--)+";
    public static final String SYMBOL_REGEX = ".";

    public abstract TextComponent parse(String text, TextComposite textComposite);
}
