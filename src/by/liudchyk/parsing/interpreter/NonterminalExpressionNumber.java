package by.liudchyk.parsing.interpreter;

/**
 * Created by Admin on 09.11.2016.
 */
public class NonterminalExpressionNumber extends AbstractMathExpression {
    private double number;

    public NonterminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
