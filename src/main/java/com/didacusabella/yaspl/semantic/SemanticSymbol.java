package com.didacusabella.yaspl.semantic;

import java.util.Objects;

public abstract class SemanticSymbol {

    private ReturnType returnType;

    public SemanticSymbol(ReturnType returnType) {
        this.returnType = returnType;
    }

    public ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
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
