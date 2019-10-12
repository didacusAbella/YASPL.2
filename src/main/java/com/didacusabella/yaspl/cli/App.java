package com.didacusabella.yaspl.cli;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.ReturnType;
import com.didacusabella.yaspl.semantic.SemanticException;
import com.didacusabella.yaspl.semantic.StackSymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.visitor.CodeVisitor;
import com.didacusabella.yaspl.visitor.SemanticVisitor;
import com.didacusabella.yaspl.visitor.SyntaxVisitor;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Main Class
 */
public class App {

  private static final String START_MESSAGE = "File not passed. Chose what file compile.\n"
          + "1)sum.yaspl\n2)product.yaspl\n3)division.yaspl\n4)pow.yaspl\n5)fibonacci.yaspl";
  private static Compiler compiler;
  private static final String[] TEST_OPTIONS = {"-xml"};

  /**
   * Main method for exec class
   *
   * @param args the srguments
   * @throws java.io.FileNotFoundException if file not found
   */
  public static void main(String[] args) throws FileNotFoundException {
    if(args.length == 0){
      System.out.println(START_MESSAGE);
      Scanner testNumber = new Scanner(System.in);
      String testFile = chooseTestFile(testNumber.nextInt());
      compiler = new Compiler(App.class.getResourceAsStream(testFile), TEST_OPTIONS);
      System.exit(0);
    } else {
      String pathName = new File(args[0]).getAbsolutePath();
      compiler = new Compiler(new FileInputStream(pathName), args);
      System.exit(0);
    }
  }

  private static String chooseTestFile(int number) {
    switch (number) {
      case 1:
        return "sum.yaspl";
      case 2:
        return "product.yaspl";
      case 3:
        return "division.yaspl";
      case 4:
        return "pow.yaspl";
      case 5:
        return "fibonacci.yaspl";
      default:
        return "test.yaspl";
    }
  }
}
