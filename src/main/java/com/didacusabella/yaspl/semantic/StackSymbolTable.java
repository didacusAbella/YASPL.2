package com.didacusabella.yaspl.semantic;
import com.didacusabella.yaspl.lexical.StringTable;

import java.util.Stack;

public class StackSymbolTable extends Stack<Scope> implements SymbolTable {

    private final StringTable tableReference;

    public StackSymbolTable(StringTable tableReference) {
        this.tableReference = tableReference;
    }

    public StringTable getTableReference() {
        return tableReference;
    }

    @Override
    public void enterScope(Scope scope) {
        this.push(scope);
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
            sb.append("Scope n"+i+"\n "+this.elementAt(i).toString().concat("\n"));
        }
        return sb.toString();
    }
}
