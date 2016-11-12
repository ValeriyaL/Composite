package by.liudchyk.parsing.action;

import by.liudchyk.parsing.entity.TextComponent;
import by.liudchyk.parsing.entity.TextComposite;
import by.liudchyk.parsing.entity.ElementType;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 09.11.2016.
 */
public class TextAction {
    public void eraseLexemes(TextComposite element, int length, char startSymbol) {
        ArrayList<TextComponent> list = element.getComponents();
        for (int index = 0; index < list.size();index++) {
            TextComponent c = list.get(index);
            if(((TextComposite) c).getElementType().equals(ElementType.LEXEME)){
                ArrayList<TextComponent> lexemes = ((TextComposite) c).getComponents();
                for(int i=0;i<lexemes.size();i++) {
                    if (lexemes.get(i).toString().trim().length() == length && lexemes.get(i).toString().trim().charAt(0) == startSymbol) {
                        lexemes.remove(i);
                    }
                }
            }else{
                eraseLexemes((TextComposite)c,length,startSymbol);
            }
        }
    }

    public ArrayList<TextComponent> sortSentences(TextComposite element){
        ArrayList<TextComponent> sent = new ArrayList<>();
        ArrayList<TextComponent> list = element.getComponents();
        for(int index=0;index<list.size();index++){
            TextComponent c = list.get(index);
            if(((TextComposite)c).getElementType().equals(ElementType.SENTENCE)){
                sent.addAll(((TextComposite) c).getComponents());
            }else{
                sent = sortSentences((TextComposite) c);
            }
        }
        Collections.sort(sent, (o1,o2)->((TextComposite)o1).getComponents().size()-((TextComposite)o2).getComponents().size());
        return sent;
    }

    public void changeLexemes(TextComposite element) {
        ArrayList<TextComponent> list = element.getComponents();
        for (int index = 0; index < list.size(); index++) {
            TextComponent c = list.get(index);
            if (((TextComposite) c).getElementType().equals(ElementType.LEXEME)) {
                ArrayList<TextComponent> lexemes = ((TextComposite) c).getComponents();
                TextComponent temp = lexemes.get(0);
                lexemes.set(0,lexemes.get(lexemes.size()-1));
                lexemes.set(lexemes.size()-1,temp);
            }else{
                changeLexemes(((TextComposite) c));
            }
        }
    }

}
