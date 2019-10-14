package com.didacusabella.yaspl.semantic;

import com.didacusabella.yaspl.lexical.StringTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Stack;

/**
 *
 * @author didacus
 */
public class StackSymbolTable implements SymbolTable {
  
  private final Stack<Integer> scopeLevel;
  private final StringTable table;
  private final LinkedHashMap<Integer, HashMap<Integer, SymbolTableRecord>> symbolTable;
  private int currentLevel;

  public StackSymbolTable(StringTable table) {
    this.table = table;
    this.scopeLevel = new Stack();
    this.symbolTable = new LinkedHashMap<>();
    this.currentLevel = 0;
  }
  
  

  @Override
  public void enterScope() {
    this.scopeLevel.push(this.currentLevel);
    this.symbolTable.put(this.scopeLevel.peek(), new HashMap<>());
    this.currentLevel++;
  }

  @Override
  public void exitScope() {
    this.scopeLevel.pop();
  }

  @Override
  public boolean probe(String lexeme) {
    int address = this.table.getAddress(lexeme);
    return this.symbolTable.get(this.scopeLevel.peek()).containsKey(address);
  }

  @Override
  public Optional<SymbolTableRecord> lookup(String lexeme) {
    int address = this.table.getAddress(lexeme);
    int size = this.scopeLevel.size();
    for(int i = size; i > 0; i--){
      int level = this.scopeLevel.elementAt(i);
      if (this.symbolTable.get(level).containsKey(address))
        return Optional.of(this.symbolTable.get(level).get(address));
    }
    return Optional.empty();
  }

  @Override
  public void addEntry(String lexeme, SymbolTableRecord str) {
    int address = this.table.getAddress(lexeme);
    this.symbolTable.get(this.scopeLevel.peek()).put(address, str);
  }
  
}
