package by.liudchyk.parsing.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Admin on 08.11.2016.
 */
public class TerminalExpressionDivide extends AbstractMathExpression {
    private final static Logger LOG = LogManager.getLogger();

    @Override
    public void interpret(Context c) {
        double div = c.popValue();
        if (div == 0) {
            LOG.warn("Divide in 0! Will be changed to 1");
            div = 1;
        }
        c.pushValue(c.popValue() / div);
    }
}
