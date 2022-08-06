package com.ivanch.littlejava;


import com.ivanch.littlejava.parser.Lexer;
import com.ivanch.littlejava.parser.Parser;
import com.ivanch.littlejava.parser.Token;
import com.ivanch.littlejava.parser.abstractsyntaxtree.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
    	
    	//загрузка программы в память
        String input = new String(Files.readAllBytes(Paths.get("./test.txt")));
        //лексический анализ (токенизация) программы
        List<Token> tokens = new Lexer(input).tokenize();
        printTokens(tokens);
        //парсинг полученных токенов
        Statement program = new Parser(tokens).parse();
        System.out.println(program.toString());
        //выполнение кода программы
        program.execute();
        
    }
    
    public static void printTokens(List<Token> tokens) {
    	for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
