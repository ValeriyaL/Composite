package by.liudchyk.parsing.interpreter;

/**
 * Created by Admin on 09.11.2016.
 */
public class TerminalExpressionIncrement extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() + 1);
    }
}
