package by.liudchyk.parsing.interpreter;

/**
 * Created by Admin on 08.11.2016.
 */
public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        double first = c.popValue();
        c.pushValue(c.popValue() - first);
    }
}
