package com.didacusabella;

import java_cup.anttask.CUPTask;
import java_cup.runtime.ComplexSymbolFactory;
import jflex.anttask.JFlexTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

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
