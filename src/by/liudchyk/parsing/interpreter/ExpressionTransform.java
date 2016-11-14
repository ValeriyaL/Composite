package by.liudchyk.parsing.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Admin on 08.11.2016.
 */
public class ExpressionTransform {

    public String transformExpToPoland(String expression) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String pref = "";
        boolean blank = true;
        String res;
        StringBuilder sbRes = new StringBuilder();
        Character prev = ' ';
        for (int i = 0; i < expression.length(); i++) {
            Character temp = expression.charAt(i);
            if (Character.isDigit(temp)) {
                if (!Character.isDigit(prev) && blank) {
                    sbRes.append(' ');
                }
                prev = temp;
                sbRes.append(temp);
                blank = true;
            } else {
                if (Character.isDigit(prev)) {
                    sbRes.append(' ');
                    if (!pref.isEmpty()) {
                        sbRes.append(pref);
                        pref = "";
                    }
                }
                if (temp.equals('-') && (prev.equals('(') || i == 0) && expression.charAt(i + 1) != '-') {
                    sbRes.append('-');
                    blank = false;
                    continue;
                }
                if (temp.equals('-') && prev.equals('-')) {
                    if (i >= 2 && Character.isDigit(expression.charAt(i - 2))) {
                        stack.pop();
                        sbRes.append("-- ");
                    } else {
                        stack.pop();
                        pref = " -- ";
                    }
                    continue;
                }
                if (i >= 2 && temp.equals('+') && prev.equals('+')) {
                    if (Character.isDigit(expression.charAt(i - 2))) {
                        stack.pop();
                        sbRes.append("++ ");
                    } else {
                        stack.pop();
                        pref = " ++ ";
                    }
                    continue;
                }
                if (temp.equals('-') || temp.equals('+') || temp.equals('*') || temp.equals('/')) {
                    Character ch = ' ';
                    while (!stack.isEmpty() && (ch = stack.pop()) == '*' || ch == '/') {
                        sbRes.append(ch.toString());
                        sbRes.append(" ");
                    }
                    stack.push(ch);
                }
                if (temp.equals('-') || temp.equals('+')) {
                    Character ch = ' ';
                    while (!stack.isEmpty() && (ch = stack.pop()) == '-' || ch == '+') {
                        sbRes.append(ch.toString());
                        sbRes.append(" ");
                    }
                    stack.push(ch);
                }
                if (!temp.equals(')')) {
                    stack.push(temp);
                    prev = temp;
                } else {
                    Character ch;
                    while (!(ch = stack.pop()).equals('(')) {
                        sbRes.append(ch.toString());
                        sbRes.append(" ");
                    }
                }
            }
        }
        if (!pref.isEmpty()) {
            sbRes.append(pref);
        }
        while (!stack.isEmpty()) {
            Character ch1 = stack.pop();
            sbRes.append(" ");
            sbRes.append(ch1.toString());
        }
        res = new String(sbRes);
        res = res.replaceAll(" +", " ");
        return res;
    }
}
