package com.didacusabella.yaspl.utils;

import java_cup.runtime.ComplexSymbolFactory;

import java.util.HashMap;

public class SymbolTable {

    private final HashMap<String, ComplexSymbolFactory.ComplexSymbol> innerTable;

    public SymbolTable(HashMap<String, ComplexSymbolFactory.ComplexSymbol> innerTable) {
        this.innerTable = innerTable;
    }

    public HashMap<String, ComplexSymbolFactory.ComplexSymbol> getInnerTable() {
        return innerTable;
    }


}
