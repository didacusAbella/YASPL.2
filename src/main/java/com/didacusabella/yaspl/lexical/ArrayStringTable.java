package com.didacusabella.yaspl.lexical;

import java.util.ArrayList;

/**
 * Implementation of String Table ADT
 */
public class ArrayStringTable implements StringTable {

    private ArrayList<LexicalSymbol> lexicalSymbols;


    /**
     * Create an empty string table
     */
    public ArrayStringTable() {
        this.lexicalSymbols = new ArrayList<>();
    }

    @Override
    public boolean addLexicalSymbol(String symbol, int code) {
        if(this.lexicalSymbols.stream().anyMatch(l -> l.getLexeme().equals(symbol)))
            return false;
        else
            return this.lexicalSymbols.add(new LexicalSymbol(symbol, code));
    }

    @Override
    public int getAddress(String lexeme) {
        return this.lexicalSymbols.indexOf(
                this.lexicalSymbols.stream()
                        .filter(l -> l.getLexeme().equals(lexeme)).findFirst().get());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lexicalSymbols.size(); i++){
            sb.append(String.format("Address:%d | Value:%s\n", i, lexicalSymbols.get(i).getLexeme()));
        }
        return sb.toString();
    }

    @Override
    public LexicalSymbol getSymbol(int address) {
        return this.lexicalSymbols.get(address);
    }

    @Override
    public String getLexeme(int address) {
        return this.lexicalSymbols.get(address).getLexeme();
    }
}
