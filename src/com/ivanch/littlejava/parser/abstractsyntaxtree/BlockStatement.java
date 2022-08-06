package com.ivanch.littlejava.parser.abstractsyntaxtree;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement implements Statement {

	private List<Statement> statements;

    public BlockStatement() {
        statements = new ArrayList<>();
    }
    
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {
        for (Statement statement : statements) {
            statement.execute();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Statement statement : statements) {
            result.append(statement.toString()).append("\n");
        }
        return result.toString();
    }
}
