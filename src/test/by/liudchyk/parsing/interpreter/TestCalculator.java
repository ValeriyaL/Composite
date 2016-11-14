package test.by.liudchyk.parsing.interpreter;

import by.liudchyk.parsing.interpreter.Calculator;
import by.liudchyk.parsing.interpreter.ExpressionTransform;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by Admin on 14.11.2016.
 */
public class TestCalculator {
    private static ExpressionTransform expressionTransform;

    @BeforeClass
    public static void initTransform() {
        expressionTransform = new ExpressionTransform();
    }

    @AfterClass
    public static void clearTransform() {
        expressionTransform = null;
    }

    @Test
    public void checkCalculate1() {
        String expression = "5+9-3-6";
        String expInPoland = expressionTransform.transformExpToPoland(expression);
        Calculator c = new Calculator(expInPoland);
        int current = c.calculate().intValue();
        int expected = 5;
        Assert.assertEquals(expected, current);
    }

    @Test
    public void checkCalculate2() {
        String expression = "(0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5)";
        String expInPoland = expressionTransform.transformExpToPoland(expression);
        Calculator c = new Calculator(expInPoland);
        int current = c.calculate().intValue();
        int expected = 6;
        Assert.assertEquals(expected, current);
    }

    @Test
    public void checkCalculate3() {
        String expression = "(3++)-5";
        String expInPoland = expressionTransform.transformExpToPoland(expression);
        Calculator c = new Calculator(expInPoland);
        int current = c.calculate().intValue();
        int expected = -1;
        Assert.assertEquals(expected, current);
    }

    @Test
    public void checkCalculate4() {
        String expression = "12-(4+5*(3+2))";
        String expInPoland = expressionTransform.transformExpToPoland(expression);
        Calculator c = new Calculator(expInPoland);
        int current = c.calculate().intValue();
        int expected = -17;
        Assert.assertEquals(expected, current);
    }

    @Test
    public void checkCalculate5() {
        String expression = "13+3-5+(-2)*3";
        String expInPoland = expressionTransform.transformExpToPoland(expression);
        Calculator c = new Calculator(expInPoland);
        int current = c.calculate().intValue();
        int expected = 5;
        Assert.assertEquals(expected, current);
    }
}
