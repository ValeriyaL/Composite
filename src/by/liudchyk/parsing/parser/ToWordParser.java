package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.Symbol;
import by.liudchyk.parsing.entity.TextType;
import by.liudchyk.parsing.interpreter.Calculator;
import by.liudchyk.parsing.interpreter.ExpressionTransform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 07.11.2016.
 */
public class ToWordParser extends AbstractParser {
    private final static Logger LOG = LogManager.getLogger();
    private ToCharacterParser next = new ToCharacterParser();
    private Pattern wordPattern = Pattern.compile(WORD_REGEX);
    private Pattern exprPattern = Pattern.compile(EXPRESSION_REGEX);

    @Override
    public TextComponent parse(String text, TextComposite composite) {
        TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);
        Matcher wordMatcher = wordPattern.matcher(text);
        Matcher exprMather = exprPattern.matcher(text);
        String textPart;
        if (wordMatcher.find()) {
            if (!Character.isAlphabetic(text.charAt(0))) {
                Symbol symbol = new Symbol(text.charAt(0));
                lexemeComposite.add(symbol);
            }
            textPart = wordMatcher.group();
            next.parse(textPart, lexemeComposite);
            if (!Character.isAlphabetic(text.charAt(text.length() - 1))) {
                Symbol symbol = new Symbol(text.charAt(text.length() - 1));
                lexemeComposite.add(symbol);
            }
        } else if (exprMather.find()) {
            ExpressionTransform expressionTransform = new ExpressionTransform();
            String expInPoland = expressionTransform.transformExpToPoland(exprMather.group());
            Calculator c = new Calculator(expInPoland);
            int i = c.calculate().intValue();
            LOG.info("expression " + expInPoland + " = " + i);
            textPart = String.valueOf(i);
            next.parse(textPart, lexemeComposite);
        }
        composite.add(lexemeComposite);
        return composite;
    }
}
