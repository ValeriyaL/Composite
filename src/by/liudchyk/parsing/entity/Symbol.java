package by.liudchyk.parsing.entity;

/**
 * Created by Admin on 01.11.2016.
 */
public class Symbol implements TextComponent {
    private char sign;

    public Symbol(char string) {
        sign = string;
    }

    public Symbol(String string) {
        sign = string.charAt(0);
    }

    @Override
    public void add(TextComponent textComponent) {
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }
}
