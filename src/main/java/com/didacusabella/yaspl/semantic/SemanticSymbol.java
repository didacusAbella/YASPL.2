package com.didacusabella.yaspl.semantic;

import java.util.Objects;

/**
 * Generic symbol to add into symbol table
 */
public abstract class SemanticSymbol {

    private final ReturnType returnType;

    /**
     * Create a new semantic symbol
     * @param returnType the type of the symbol
     */
    public SemanticSymbol(ReturnType returnType) {
        this.returnType = returnType;
    }

    /**
     * Get the type of this symbol
     * @return the kind of the symbol
     */
    public ReturnType getReturnType() {
        return returnType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemanticSymbol that = (SemanticSymbol) o;
        return returnType == that.returnType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnType);
    }

    @Override
    public String toString() {
        return "SemanticSymbol{" +
                "returnType=" + returnType +
                '}';
    }
}
