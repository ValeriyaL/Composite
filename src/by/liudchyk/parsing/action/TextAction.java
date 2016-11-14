package by.liudchyk.parsing.action;

import by.liudchyk.parsing.entity.TextType;
import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 09.11.2016.
 */
public class TextAction {
    public void eraseLexemes(TextComposite element, int length, char startSymbol) {
        ArrayList<TextComponent> list = element.getComponents();
        for (int index = 0; index < list.size(); index++) {
            TextComponent c = list.get(index);
            if (((TextComposite) c).getTextType().equals(TextType.LEXEME)) {
                String lexeme = c.toString().trim();
                if (lexeme.length() == length && lexeme.charAt(0) == startSymbol) {
                    list.remove(index);
                }
            } else {
                eraseLexemes((TextComposite) c, length, startSymbol);
            }
        }
    }

    public ArrayList<TextComponent> sortSentences(TextComposite element) {
        ArrayList<TextComponent> sent = new ArrayList<>();
        ArrayList<TextComponent> list = element.getComponents();
        for (TextComponent c : list) {
            if (((TextComposite) c).getTextType().equals(TextType.SENTENCE)) {
                sent.add(c);
            } else {
                sent.addAll(sortSentences((TextComposite) c));
            }
        }
        Collections.sort(sent, (o1, o2) -> ((TextComposite) o1).getComponents().size() - ((TextComposite) o2).getComponents().size());
        return sent;
    }

    public void changeLexemes(TextComposite element) {
        ArrayList<TextComponent> list = element.getComponents();
        for (TextComponent c : list) {
            if (((TextComposite) c).getTextType().equals(TextType.SENTENCE)) {
                ArrayList<TextComponent> lexemes = ((TextComposite) c).getComponents();
                TextComponent temp = lexemes.get(0);
                lexemes.set(0, lexemes.get(lexemes.size() - 1));
                lexemes.set(lexemes.size() - 1, temp);
            } else {
                changeLexemes(((TextComposite) c));
            }
        }
    }

}
