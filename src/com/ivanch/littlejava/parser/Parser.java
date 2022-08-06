package com.ivanch.littlejava.parser;

import java.util.List;
import com.ivanch.littlejava.parser.abstractsyntaxtree.*;

public class Parser {

	

    private List<Token> tokens;
    private int size;
    private int currentPos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }
    
    public Statement parse() {
        BlockStatement result = new BlockStatement();
        while (!match(TokenType.EOF)) {
            result.add(statement());
        }
        return result;
    }
    
    private Statement block() {
        BlockStatement block = new BlockStatement();
        consume(TokenType.LBRACE);
        while (!match(TokenType.RBRACE)) {
            block.add(statement());
        }
        return block;
    }
    
    private Statement statementOrBlock() {
        if (get(0).getType() == TokenType.LBRACE) return block();
        return statement();
    }
    
    private Statement statement() {
        if (match(TokenType.PRINT)) 
            	return new PrintStatement(expression());
        
        if (match(TokenType.IF)) 
            	return ifElse();
        
        if (match(TokenType.WHILE)) 
            	return whileStatement();
        
        if (match(TokenType.BREAK)) 
            	return new BreakStatement();
        
        if (match(TokenType.CONTINUE)) 
            	return new ContinueStatement();
        
        if (match(TokenType.FOR)) 
            	return forStatement();
        
        return assignmentStatement();
    }
    
    private Statement assignmentStatement() { // WORD EQ
        Token current = get(0);
        if (match(TokenType.WORD) && get(0).getType() == TokenType.EQ) {
            String variable = current.getText();
            consume(TokenType.EQ);
            return new AssignmentStatement(variable, expression());
        }
        throw new RuntimeException("Unknown statement");
    }
    
    private Statement ifElse() {
        Expression condition = expression();
        Statement ifStatement = statementOrBlock();
        Statement elseStatement;
        if (match(TokenType.ELSE)) {
            elseStatement = statementOrBlock();
        } else {
            elseStatement = null;
        }
         return new IfStatement(condition, ifStatement, elseStatement);
    }
    
    private Statement whileStatement() {
        Expression condition = expression();
        Statement statement = statementOrBlock();
        return new WhileStatement(condition, statement);
    }
    
    private Statement forStatement() {
        Statement initialization = assignmentStatement();
        consume(TokenType.COMMA);
        Expression termination = expression();
        consume(TokenType.COMMA);
        Statement increment = assignmentStatement();
        Statement statement = statementOrBlock();
        return new ForStatement(initialization, termination, increment, statement);
    }
    
    
    private Expression expression() {
        return logicalOr();
    }
    
    private Expression logicalOr() {
        Expression result = logicalAnd();
        
        while (true) {
            if (match(TokenType.BARBAR)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.OR, result, logicalAnd());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression logicalAnd() {
        Expression result = equality();
        
        while (true) {
            if (match(TokenType.AMPAMP)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.AND, result, equality());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression equality() {
        Expression result = conditional();
        
        if (match(TokenType.EQEQ)) {
            return new ConditionalExpression(ConditionalExpression.Operator.EQUALS, result, conditional());
        }
        if (match(TokenType.EXCLEQ)) {
            return new ConditionalExpression(ConditionalExpression.Operator.NOT_EQUALS, result, conditional());
        }
        
        return result;
    }
    
    private Expression conditional() {
        Expression result = additive();
        
        if (match(TokenType.LT)) 
            	result = new ConditionalExpression(ConditionalExpression.Operator.LT, result, additive());
        
        if (match(TokenType.LTEQ)) 
            	result = new ConditionalExpression(ConditionalExpression.Operator.LTEQ, result, additive());
            
        if (match(TokenType.GT)) 
            	result = new ConditionalExpression(ConditionalExpression.Operator.GT, result, additive());
        
        if (match(TokenType.GTEQ)) 
            	result = new ConditionalExpression(ConditionalExpression.Operator.GTEQ, result, additive());
        
        return result;
    }
    
    private Expression additive() {
        Expression result = multiplicative();
        
        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression multiplicative() {
        Expression result = unary();
        
        while (true) {
            if (match(TokenType.STAR)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.SLASH)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }
    
    private Expression primary() {
        Token current = get(0);
        if (match(TokenType.NUMBER)) 
            	return new ValueExpression(Double.parseDouble(current.getText()));
        
        if (match(TokenType.HEX_NUMBER)) 
            	return new ValueExpression(Long.parseLong(current.getText(), 16));
        
        if (match(TokenType.WORD)) 
            	return new VariableExpression(current.getText());
        
        if (match(TokenType.TEXT)) 
            	return new ValueExpression(current.getText());
        
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }
    
    private Token consume(TokenType type) {
        Token current = get(0);
        if (type != current.getType()) throw new RuntimeException("Token " + current + " doesn't match " + type);
        currentPos++;
        return current;
    }
    
    private boolean match(TokenType type) {
        Token current = get(0);
        if (type != current.getType()) return false;
        currentPos++;
        return true;
    }
    
    private Token get(int relativePosition) {
        int position = currentPos + relativePosition;
        if (position >= size) return new Token(TokenType.EOF, "");
        return tokens.get(position);
    }
}
