package by.liudchyk.parsing.interpreter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Admin on 09.11.2016.
 */
public class Calculator {
    private ArrayList<AbstractMathExpression> listExpression;
    public Calculator(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    public void parse(String expression){
        for (String lexeme : expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            if(lexeme.length()==2){
                char temp = lexeme.charAt(1);
                switch (temp){
                    case '+':
                        listExpression.add(new TerminalExpressionIncrement());
                        break;
                    case '-':
                        listExpression.add(new TerminalExpressionDecrement());
                    default:
                        Scanner scan = new Scanner(lexeme);
                        if (scan.hasNextInt()) {
                            listExpression.add(new NonterminalExpressionNumber(scan.nextInt()));
                        }
                }
            }else if(lexeme.length()==1){
                char temp = lexeme.charAt(0);
                switch (temp) {
                    case '+':
                        listExpression.add(new TerminalExpressionPlus());
                        break;
                    case '-':
                        listExpression.add(new TerminalExpressionMinus());
                        break;
                    case '*':
                        listExpression.add(new TerminalExpressionMultiply());
                        break;
                    case '/':
                        listExpression.add(new TerminalExpressionDivide());
                        break;
                    default:
                        Scanner scan = new Scanner(lexeme);
                        if (scan.hasNextInt()) {
                            listExpression.add(new NonterminalExpressionNumber(scan.nextInt()));
                        }
                }
            }else{
                Scanner scan = new Scanner(lexeme);
                if (scan.hasNextInt()) {
                    listExpression.add(new NonterminalExpressionNumber(scan.nextInt()));
                }
            }
        }
    }
    public Number calculate() {
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}

