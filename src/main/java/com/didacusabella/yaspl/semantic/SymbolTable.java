package com.didacusabella.yaspl.semantic;

import com.didacusabella.yaspl.lexical.StringTable;

public interface SymbolTable {

    void enterScope();
    void exitScope();
    boolean probe(int address);
    void addId(int address, SemanticSymbol symbol);
    Scope lookup(int address);
    int findAddress(String lexeme);
    Scope getCurrentScope();
    StringTable getTable();
}
