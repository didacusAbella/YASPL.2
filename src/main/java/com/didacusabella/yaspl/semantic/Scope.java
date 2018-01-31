package com.didacusabella.yaspl.semantic;

import java.util.HashMap;

public class Scope extends HashMap<Integer, SemanticSymbol>{


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.entrySet().forEach(e -> {
            sb.append(String.format("Indirizzo:%d | %s\n", e.getKey(), e.getValue()));
        });
        return sb.toString();
    }
}
