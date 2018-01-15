package com.didacusabella.yaspl;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) throws Exception {
        ComplexSymbolFactory cf = new ComplexSymbolFactory();
        FileInputStream fis = new FileInputStream(new File(App.class.getResource("/prova.yasp").getPath()));
        Parser parser = new Parser(new Lexer(cf, fis), cf);
        parser.parse();
    }

}
