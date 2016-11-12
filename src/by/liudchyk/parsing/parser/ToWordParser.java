package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.Symbol;
import by.liudchyk.parsing.entity.ElementType;
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
        TextComposite wordComposite = new TextComposite(ElementType.WORD);
        LOG.info("Word composite was created");
        Matcher wordMatcher = wordPattern.matcher(text);
        Matcher exprMather = exprPattern.matcher(text);
        String textPart;
        if(wordMatcher.find()){
            if(!Character.isAlphabetic(text.charAt(0))){
                Symbol symbol = new Symbol(text.charAt(0));
                wordComposite.add(symbol);
            }
            textPart = wordMatcher.group();
            next.parse(textPart,wordComposite);
            if(!Character.isAlphabetic(text.charAt(text.length()-1))){
                Symbol symbol = new Symbol(text.charAt(text.length()-1));
                wordComposite.add(symbol);
            }
        }else if(exprMather.find()){
            ExpressionTransform expressionTransform = new ExpressionTransform();
            String expInPoland = expressionTransform.transformExpToPoland(exprMather.group());
            Calculator c = new Calculator(expInPoland);
            int i = c.calculate().intValue();
            textPart = String.valueOf(i);
            next.parse(textPart,wordComposite);
        }
        composite.add(wordComposite);
        return composite;
    }
}
