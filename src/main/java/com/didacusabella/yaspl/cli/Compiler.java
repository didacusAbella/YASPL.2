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
import com.didacusabella.yaspl.template.CTemplate;
import com.didacusabella.yaspl.template.SymbolTableTemplate;
import com.didacusabella.yaspl.template.XmlTemplate;
import com.didacusabella.yaspl.visitor.CLangVisitor;
import com.didacusabella.yaspl.visitor.ContextVisitor;
import com.didacusabella.yaspl.visitor.TypeCheckVisitor;
import com.didacusabella.yaspl.visitor.XmlVisitor;
import java.io.InputStream;

import java_cup.runtime.ComplexSymbolFactory;
import org.w3c.dom.Document;

/**
 * Yaspl Compiler. It is a multi-pass compiler
 *
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
   *
   * @param options the option to customize compilation. See above for some example
   * @param inputStream the bute stream to process
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
      Program root = Program.class.cast(this.parser.parse().value);
      ContextVisitor contextVisitor = new ContextVisitor(this.errorHandler);
      root.accept(contextVisitor, this.symbolTable);
      TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(this.errorHandler);
      root.accept(typeCheckVisitor, new FakeSymbolTable((StackSymbolTable) this.symbolTable,
              this.stringTable));
      if (this.errorHandler.haveErrors()) {
        throw new SemanticException("Error during semantic analysis");
      }
      CTemplate template = new CTemplate();
      String model = template.create().get();
      CLangVisitor cLangVisitor = new CLangVisitor(model);
      model = root.accept(cLangVisitor, new FakeSymbolTable((StackSymbolTable) this.symbolTable,
              this.stringTable));
      template.write(this.options[0], model);
      handleOptions(root);
    } catch (SemanticException ex) {
      this.errorHandler.logErrors();
    } catch (Exception ex) {
      System.err.println("Errore durante il parsing");
      ex.printStackTrace();
    }
  }

  private boolean hasOption(String option) {
    for (String opt : this.options) {
      if (opt.equals(option)) {
        return true;
      }
    }
    return false;
  }

  private void handleOptions(Program program) {
    if(this.hasOption("-xml")){
      XmlTemplate xTemplate = new XmlTemplate();
      Document xmlModel = xTemplate.create().get();
      XmlVisitor xmlVisitor = new XmlVisitor();
      program.accept(xmlVisitor, xmlModel);
      xTemplate.write(this.options[0], xmlModel);
    } 
    if (this.hasOption("-sym")){
      SymbolTableTemplate stt = new SymbolTableTemplate();
      stt.write(this.options[0], this.symbolTable.toString());
    }
  }

}
