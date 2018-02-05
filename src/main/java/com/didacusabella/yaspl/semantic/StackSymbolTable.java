package com.didacusabella.yaspl.semantic;
import com.didacusabella.yaspl.lexical.StringTable;

import java.util.Stack;

/**
 * Implementation of Symbol table ADT
 */
public class StackSymbolTable extends Stack<Scope> implements SymbolTable {

    private final StringTable tableReference;

    /**
     * Create a new Stack symbol Table
     * @param tableReference the string table referencies
     */
    public StackSymbolTable(StringTable tableReference) {
        this.tableReference = tableReference;
    }

    @Override
    public StringTable getTable() {
        return tableReference;
    }

    @Override
    public void enterScope() {
        this.push(new Scope());
    }

    @Override
    public void exitScope() {
        this.pop();
    }

    @Override
    public boolean probe(int address)
    {
        return this.getCurrentScope().containsKey(address);
    }

    @Override
    public void addId(int address, SemanticSymbol symbol) {
        this.getCurrentScope().put(address, symbol);
    }

    @Override
    public Scope lookup(int address) {
        for(int i = this.size() - 1; i >= 0; i--)
            if(this.elementAt(i).containsKey(address))
                return this.elementAt(i);
        return null;
    }

    @Override
    public int findAddress(String lexeme) {
        return this.tableReference.getAddress(lexeme);
    }

    @Override
    public Scope getCurrentScope() {
        return this.peek();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < this.size(); i++){
            sb.append("Scope n°").append(i).append('\n').append(this.elementAt(i).toString()).append('\n');
        }
        return sb.toString();
    }
}
