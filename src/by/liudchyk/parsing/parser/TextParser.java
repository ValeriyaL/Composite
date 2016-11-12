package by.liudchyk.parsing.parser;

import by.liudchyk.parsing.entity.TextComposite;

/**
 * Created by Admin on 08.11.2016.
 */
public class TextParser extends AbstractParser {
    private ToParagraphParser next = new ToParagraphParser();

    @Override
    public TextComposite parse(String text, TextComposite textComposite) {
        next.parse(text, textComposite);
        return textComposite;
    }
}
