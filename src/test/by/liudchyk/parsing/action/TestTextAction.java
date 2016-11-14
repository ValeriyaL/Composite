package test.by.liudchyk.parsing.action;

import by.liudchyk.parsing.action.TextAction;
import by.liudchyk.parsing.creator.TextCreator;
import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.TextType;
import by.liudchyk.parsing.parser.TextParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Admin on 14.11.2016.
 */
public class TestTextAction {
    private TextAction textAction;
    private TextComposite c;
    private TextParser tp;

    @Before
    public void initTextAction() {
        textAction = new TextAction();
        c = new TextComposite(TextType.TEXT);
        tp = new TextParser();
    }

    @After
    public void clearTextAction() {
        textAction = null;
        c = null;
        tp = null;
    }

    @Test
    public void checkEraseLexemes() {
        int length = 4;
        char firstSymbol = 'A';
        String text = TextCreator.readTextFromFile("data/testErasing.txt");
        TextComposite textComp = tp.parse(text, c);
        textAction.eraseLexemes(textComp, length, firstSymbol);
        String current = textComp.toString().trim();
        String expected = "wants to be a doctor, but he will be a teacher.";
        Assert.assertTrue(current.equals(expected));
    }

    @Test
    public void checkSortSentences() {
        String text = TextCreator.readTextFromFile("data/testSorting.txt");
        TextComposite textComp = tp.parse(text, c);
        ArrayList<TextComponent> currentList = textAction.sortSentences(textComp);
        String current = currentList.toString();
        ArrayList<String> expectedList = new ArrayList<String>() {
            {
                add("  Wow.");
                add("  Sasha is cool.");
                add("  Funky Monkey is some staff.");
                add("  All students want to sleep all day long, but they have to STUDY.");
            }
        };
        String expected = expectedList.toString();
        Assert.assertTrue(current.equals(expected));
    }

    @Test
    public void checkChangeLexemes() {
        String text = TextCreator.readTextFromFile("data/testExchanging.txt");
        TextComposite textComp = tp.parse(text, c);
        textAction.changeLexemes(textComp);
        String current = textComp.toString();
        String expected = "  cool. is Sasha  STUDY. students want to sleep all day long, but they have to All  Wow.\n  staff. Monkey is some Funky\n";
        Assert.assertTrue(current.equals(expected));
    }
}
