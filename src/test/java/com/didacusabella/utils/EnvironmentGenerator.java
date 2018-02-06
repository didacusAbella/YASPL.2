package com.didacusabella.utils;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.StackSymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.visitor.SemanticVisitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

public class EnvironmentGenerator {

    private static Parser parser;
    private static StringTable st;


    public static Program setupEnvironment(String fileName) throws Exception {
        ComplexSymbolFactory csf = new ComplexSymbolFactory();
        st = new ArrayStringTable();
        InputStream fis = EnvironmentGenerator.class.getClassLoader().getResourceAsStream(fileName);
        parser = new Parser(new Lexer(csf, fis, st), csf);
        SemanticVisitor sm = new SemanticVisitor(new StackSymbolTable(st));
        Program p = (Program)parser.parse().value;
        sm.visit(p, Logger.getLogger("test"));
        return p;
    }

}
