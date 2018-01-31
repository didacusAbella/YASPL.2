package com.didacusabella.yaspl.lexical;

public interface StringTable {
    public boolean addLexicalSymbol(String symbol, int code);
    public int getAddress(String lexeme);
}
