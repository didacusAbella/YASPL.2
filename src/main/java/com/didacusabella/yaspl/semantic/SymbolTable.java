package com.didacusabella.yaspl.semantic;

public interface SymbolTable {

    void enterScope(Scope scope);
    void exitScope();
    boolean probe(SemanticSymbol symbol);
    void addId(int address, SemanticSymbol symbol);
    Scope lookup(int address);
    int findAddress(String lexeme);
    Scope getCurrentScope();
}
