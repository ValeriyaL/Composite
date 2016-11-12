package by.liudchyk.parsing.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Admin on 08.11.2016.
 */
public class Context {
    private ArrayDeque<Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues.pop();
    }

    public void pushValue(Double value) {
        this.contextValues.push(value);
    }
}
