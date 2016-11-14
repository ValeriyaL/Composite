package by.liudchyk.parsing.interpreter;

/**
 * Created by Admin on 08.11.2016.
 */
public class TerminalExpressionMultiply extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() * c.popValue());
    }
}
