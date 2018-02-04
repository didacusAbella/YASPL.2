package com.didacusabella.yaspl.semantic;

import com.didacusabella.yaspl.lexical.StringTable;

/**
 * A symbol table is used during compiling for semantic analysis and code generation
 */
public interface SymbolTable {
    /**
     * Create a new scope
     */
    void enterScope();

    /**
     * exit from the current scope
     */
    void exitScope();

    /**
     * check if a symbol is present in the current scope
     * @param address the addres of the identifier
     * @return true if is present
     */
    boolean probe(int address);

    /**
     * Add a new symbol in the current scope
     * @param address the address of the new symbol
     * @param symbol the semantic symbol associated
     */
    void addId(int address, SemanticSymbol symbol);

    /**
     * find if an identifier is the table
     * @param address the address of the identifier
     * @return The scope where the identifier is defined. Null otherwise
     */
    Scope lookup(int address);

    /**
     * Find the address for a specific identifier name
     * @param lexeme the name
     * @return the address associated
     */
    int findAddress(String lexeme);

    /**
     * Get the current scope in table
     * @return the current scope
     */
    Scope getCurrentScope();

    /**
     * get the string table used
     * @return the string table
     */
    StringTable getTable();
}
