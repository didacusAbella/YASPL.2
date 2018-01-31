package com.didacusabella.yaspl.lexical;

import java.util.ArrayList;

public class ArrayStringTable implements StringTable {

    private ArrayList<LexicalSymbol> lexicalSymbols;

    public ArrayStringTable(ArrayList<LexicalSymbol> lexicalSymbols) {
        this.lexicalSymbols = lexicalSymbols;
    }

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
            sb.append(String.format("Indirizzo:%d | Valore:%s\n", i, lexicalSymbols.get(i).getLexeme()));
        }
        return sb.toString();
    }
}
