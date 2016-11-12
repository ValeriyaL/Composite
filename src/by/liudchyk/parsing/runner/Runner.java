package by.liudchyk.parsing.runner;

import by.liudchyk.parsing.action.TextAction;
import by.liudchyk.parsing.creator.TextCreator;
import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.ElementType;
import by.liudchyk.parsing.parser.*;
import by.liudchyk.parsing.writer.TextWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 01.11.2016.
 */
public class Runner {
    private static final Logger LOG = LogManager.getLogger();
    private static final String INPUT_FILE = "data/text.txt";
    private static final String OUTPUT_FILE = "data/output.txt";
    private static final String SORTED_FILE = "data/sorted.txt";
    private static final String DELETED_OUT  = "data/deleted.txt";
    private static final String CHANGED_OUT = "data/changed.txt";
    private static final int LEXEME_LENGTH = 4;
    private static final char FIRST_CHARACTER = 'f';

    public static void main(String[] args) {
        String text = TextCreator.readTextFromFile(INPUT_FILE);
        TextComposite textComposite = new TextComposite(ElementType.TEXT);
        TextParser tp = new TextParser();
        TextComposite c = tp.parse(text,textComposite);
        TextWriter.writeToFile(OUTPUT_FILE, c.toString());
        TextAction la = new TextAction();
        ArrayList<TextComponent> sorted = la.sortSentences(c);
        TextWriter.writeToFile(SORTED_FILE, sorted.toString());
        la.eraseLexemes(c,LEXEME_LENGTH, FIRST_CHARACTER);
        TextWriter.writeToFile(DELETED_OUT, c.toString());
        la.changeLexemes(c);
        TextWriter.writeToFile(CHANGED_OUT, c.toString());
    }
}
