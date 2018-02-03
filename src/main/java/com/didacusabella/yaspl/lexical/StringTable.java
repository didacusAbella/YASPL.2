package com.didacusabella.yaspl.lexical;

public interface StringTable {
    boolean addLexicalSymbol(String symbol, int code);
    int getAddress(String lexeme);
    LexicalSymbol getSymbol(int address);
    String getLexeme(int address);
}
