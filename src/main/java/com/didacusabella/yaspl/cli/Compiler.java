package com.didacusabella.yaspl.cli;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.template.XmlTemplate;
import com.didacusabella.yaspl.visitor.XmlVisitor;
import java.io.InputStream;
import java_cup.runtime.ComplexSymbolFactory;

/**
 * Yaspl Compiler. It is a multi-pass compiler
 * @author didacus
 * @version 1.0
 * @since 2.0
 */
public class Compiler {
  
  private final Lexer lexer;
  private final Parser parser;
  private final ComplexSymbolFactory symbolFactory;
  //private final SymbolTable symbolTable;
  private final String[] options;
  private final StringTable stringTable;

  /**
   * Create a new compiler 
   * @param options the option to customize compilation. See above for some example
   * @param inputStream the bute stream to process
   * @version 1.0
   * @since 2.0
   */
  public Compiler(InputStream inputStream, String[] options) {
    this.options = options;
    this.symbolFactory = new ComplexSymbolFactory();
    this.stringTable = new ArrayStringTable();
    this.lexer = new Lexer(this.symbolFactory, inputStream, this.stringTable);
    this.parser = new Parser(this.lexer, this.symbolFactory);
  }
  
  /**
   * Compile the file passed
   * @throws java.lang.Exception
   */
  public void compileFile() throws Exception {
    Program program = Program.class.cast(this.parser.parse().value);
    if(this.hasOption("-xml")){
      XmlTemplate xmlTemplate = new XmlTemplate();
      XmlVisitor visitor = new XmlVisitor();
      program.accept(visitor, xmlTemplate.getDocument());
      xmlTemplate.render(this.options[0]);
    }
  }
  
  
  private boolean hasOption(String option){
    for(String opt : this.options)
      if(opt.equals(option))
        return true;
    return false;
  }
  
  
}
