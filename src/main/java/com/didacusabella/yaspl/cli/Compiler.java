package com.didacusabella.yaspl.cli;

import com.didacusabella.yaspl.dist.Lexer;
import com.didacusabella.yaspl.dist.Parser;
import com.didacusabella.yaspl.error.ErrorHandler;
import com.didacusabella.yaspl.error.StackErrorHandler;
import com.didacusabella.yaspl.lexical.ArrayStringTable;
import com.didacusabella.yaspl.lexical.StringTable;
import com.didacusabella.yaspl.semantic.FakeSymbolTable;
import com.didacusabella.yaspl.semantic.SemanticException;
import com.didacusabella.yaspl.semantic.StackSymbolTable;
import com.didacusabella.yaspl.semantic.SymbolTable;
import com.didacusabella.yaspl.syntax.Program;
import com.didacusabella.yaspl.template.XmlTemplate;
import com.didacusabella.yaspl.visitor.CLangVisitor;
import com.didacusabella.yaspl.visitor.ContextVisitor;
import com.didacusabella.yaspl.visitor.TypeCheckVisitor;
import com.didacusabella.yaspl.visitor.XmlVisitor;
import java.io.InputStream;
import java_cup.runtime.ComplexSymbolFactory;
import javax.xml.parsers.ParserConfigurationException;

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
  private final SymbolTable symbolTable;
  private final String[] options;
  private final StringTable stringTable;
  private final ErrorHandler errorHandler;

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
    this.symbolTable = new StackSymbolTable(this.stringTable);
    this.errorHandler = new StackErrorHandler();
  }
  
  /**
   * Compile the file passed
   */
  public void compileFile() {
    try {
      Program program = Program.class.cast(this.parser.parse().value);
      ContextVisitor contextVisitor = new ContextVisitor(this.errorHandler);
      program.accept(contextVisitor, this.symbolTable);
      TypeCheckVisitor typeVisitor = new TypeCheckVisitor(this.errorHandler);
      program.accept(typeVisitor, new FakeSymbolTable((StackSymbolTable) this.symbolTable, 
              this.stringTable));
      if(this.errorHandler.haveErrors()){
        throw new SemanticException("There are some errors during semantic analysis");
      } else {
        CLangVisitor code = new CLangVisitor("");
        program.accept(code, new FakeSymbolTable((StackSymbolTable) this.symbolTable, this.stringTable));
      }
      if(this.hasOption("-xml")){
        XmlTemplate xmlTemplate = new XmlTemplate();
        XmlVisitor visitor = new XmlVisitor();
        program.accept(visitor, xmlTemplate.getDocument());
        xmlTemplate.render(this.options[0]);
      }
    } catch (SemanticException ex) {
      System.err.println(ex);
      this.errorHandler.logErrors();
    } catch (ParserConfigurationException ex) {
      System.err.println("Error during XML creation");
    } catch (Exception ex) {
      System.err.println(ex);
    }
  }
  
  
  private boolean hasOption(String option){
    for(String opt : this.options)
      if(opt.equals(option))
        return true;
    return false;
  }
  
  
}
