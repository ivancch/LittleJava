package com.ivanch.littlejava.parser.abstractsyntaxtree;

import com.ivanch.littlejava.lib.NumberValue;
import com.ivanch.littlejava.lib.StringValue;
import com.ivanch.littlejava.lib.Value;

public class BinaryExpression implements Expression {

	 private Expression expr1, expr2;
	    private char operation;

	    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
	        this.operation = operation;
	        this.expr1 = expr1;
	        this.expr2 = expr2;
	    }

	    @Override
	    public Value eval() {
	        Value value1 = expr1.eval();
	        Value value2 = expr2.eval();
	        if (value1 instanceof StringValue) {
	            String string1 = value1.asString();
	            switch (operation) {
//                  case ' ': ...add other string operations...
	                case '+':
	                default:
	                    return new StringValue(string1 + value2.asString());
	            }
	        }
	        
	        double number1 = value1.asNumber();
	        double number2 = value2.asNumber();
	        switch (operation) {
	            case '-': return new NumberValue(number1 - number2);
	            case '*': return new NumberValue(number1 * number2);
	            case '/': return new NumberValue(number1 / number2);
	            case '+':
	            default:
	                return new NumberValue(number1 + number2);
	        }
	    }

	    @Override
	    public String toString() {
	        return String.format("[%s %c %s]", expr1, operation, expr2);
	    }
}
