package by.liudchyk.parsing.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Admin on 01.11.2016.
 */
public class TextComposite implements TextComponent{
    private static final Logger LOG = LogManager.getLogger();
    private ArrayList<TextComponent> components = new ArrayList<>();
    private ElementType elementType;

    public TextComposite(ElementType elementType) {
        this.elementType = elementType;
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (elementType){
            case TEXT:
                appendComponents(sb);
                break;
            case PARAGRAPH:
                appendComponents(sb);
                break;
            case SENTENCE:
                sb.append("\n");
            case LEXEME:
                appendComponents(sb);
                break;
            case WORD:
                sb.append(" ");
            case SYMBOL:
                appendComponents(sb);
                break;
        }
        return new String(sb);
    }

    private void appendComponents(StringBuilder sb) {
        components.clone();
        for (TextComponent c : components) {
            sb.append(c);
        }
    }

    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    public ElementType getElementType() {
        return elementType;
    }
}
