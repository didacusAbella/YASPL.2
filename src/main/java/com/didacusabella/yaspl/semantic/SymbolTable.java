package com.didacusabella.yaspl.semantic;

import java.util.Optional;

/**
 * A symbol table is used during compiling for semantic analysis and code generation
 */
public interface SymbolTable {
   
  /**
   * Enter in  a new scope
   */
  void enterScope();
  
  /**
   * Exit from the current scope
   */
  void exitScope();
  
  /**
   * Check if the lexeme is present in the current scope
   * @param lexeme the lexeme to find
   * @return true if is present in the current scope
   */
  boolean probe(String lexeme);
  
  /**
   * Search for a lexeme in the scope hierarchy
   * @param lexeme the lexeme to find
   * @return an optional record associated
   */
  Optional<SymbolTableRecord> lookup(String lexeme);
  
  /**
   * Add a new entry in the current scope
   * @param lexeme the lexeme
   * @param str the record associated
   */
  void addEntry(String lexeme, SymbolTableRecord str);
}
