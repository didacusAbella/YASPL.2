package com.didacusabella.yaspl.semantic;

import java.util.HashMap;

/**
 * A scope is used to handle programming language block scoped. Each scope is ana hash map that contain the address
 * of the identifier and a Semantic symbol associated
 */
public class Scope extends HashMap<Integer, SemanticSymbol>{


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEach((key, value) ->  sb.append(String.format("Address:%d | %s\n", key, value)));
        return sb.toString();
    }
}
