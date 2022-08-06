package com.ivanch.littlejava.parser.abstractsyntaxtree;

import com.ivanch.littlejava.lib.NumberValue;
import com.ivanch.littlejava.lib.StringValue;
import com.ivanch.littlejava.lib.Value;

public class ValueExpression implements Expression {
	
	
    private Value value;
    
    public ValueExpression(double value) {
        this.value = new NumberValue(value);
    }
    
    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }

    @Override
    public Value eval() {
        return value;
    }

    @Override
    public String toString() {
        return value.asString();
    }
}
