package com.ivanch.littlejava.parser.abstractsyntaxtree;

public class ForStatement implements Statement {
	
	
	private Statement initialization;
    private Expression termination;
    private Statement increment;
    private Statement statement;

    
    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.statement = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().asNumber() != 0; increment.execute()) {
            try {
                statement.execute();
            } catch (BreakStatement bs) {
                break;
            } catch (ContinueStatement cs) {
//                 continue;
            }
        }
    }

    @Override
    public String toString() {
    	return String.format("for %s, %s, %s %s", initialization, termination, increment, statement);
    }
    
}
