package com.didacusabella.yaspl;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.StackSymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.visitor.CodeVisitor;
import com.didacusabella.yaspl.visitor.SemanticVisitor;
import com.didacusabella.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    private static StringTable table;
    private static SymbolTable symbolTable;
    private static ComplexSymbolFactory cf;
    private static SemanticVisitor semanticVisitor;
    private static Lexer lexer;
    private static Parser parser;
    private static CodeVisitor cVisitor;


    public static void main( String[] args ) throws Exception {
        cf = new ComplexSymbolFactory();
        FileInputStream fis = new FileInputStream(new File(App.class.getResource("/prova.yasp").getPath()));
        table = new ArrayStringTable();
        lexer = new Lexer(cf, fis, table);
        parser = new Parser(lexer, cf);
        Program program = (Program) parser.parse().value;
        symbolTable = new StackSymbolTable(table);
        semanticVisitor = new SemanticVisitor(symbolTable);
        semanticVisitor.visit(program, Logger.getLogger("YASPL2 SEMANTIC VISIT"));
        cVisitor = new CodeVisitor(symbolTable);
        File f = new File(System.getProperty("user.home").concat("/output.c"));
        f.createNewFile();
        FileWriter pw = new FileWriter(f);
        pw.write(cVisitor.visit(program, null));
        pw.close();
    }

}
