package com.ivanch.littlejava.parser.abstractsyntaxtree;

import com.ivanch.littlejava.lib.Value;
import com.ivanch.littlejava.lib.Variables;

public class VariableExpression implements Expression {
	
	
    private String name;
    
    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!Variables.isExists(name))
        	throw new RuntimeException("constant/variable does not exist");
        return Variables.get(name);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
