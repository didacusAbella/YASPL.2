package com.didacusabella.yaspl.lexical;

/**
 * A string table is used to memorize the identifier encountered during lexical analysis
 */
public interface StringTable {
    /**
     * Add a new lexical symbol to table
     * @param symbol the symbol name
     * @param code the code associated
     * @return true if addedd successfully
     */
    boolean addLexicalSymbol(String symbol, int code);

    /**
     * Get the addres for a specific lexeme
     * @param lexeme the lexeme for searching
     * @return the address of the lexeme
     */
    int getAddress(String lexeme);

    /**
     * Find a lexical symbol associated with a specific address
     * @param address the address
     * @return the lexical symbol
     */
    LexicalSymbol getSymbol(int address);

    /**
     * Get the lexeme associated with this address
     * @param address the address to search
     * @return the lexeme associated
     */
    String getLexeme(int address);
}
