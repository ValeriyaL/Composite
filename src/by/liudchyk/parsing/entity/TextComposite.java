package by.liudchyk.parsing.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Admin on 01.11.2016.
 */
public class TextComposite implements TextComponent {
    private static final Logger LOG = LogManager.getLogger();
    private ArrayList<TextComponent> components = new ArrayList<>();
    private TextType textType;

    public TextComposite(TextType textType) {
        this.textType = textType;
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (textType) {
            case TEXT:
                appendComponents(sb);
                break;
            case PARAGRAPH:
                appendComponents(sb);
                sb.append("\n");
                break;
            case SENTENCE:
            case LEXEME:
                sb.append(" ");
                appendComponents(sb);
                break;
            case WORD:
                //          case SYMBOL:
                appendComponents(sb);
                break;
            default:
                LOG.error("No such text type: " + textType);
        }
        return new String(sb);
    }

    private void appendComponents(StringBuilder sb) {
        for (TextComponent c : components) {
            sb.append(c);
        }
    }

    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    public TextType getTextType() {
        return textType;
    }
}
