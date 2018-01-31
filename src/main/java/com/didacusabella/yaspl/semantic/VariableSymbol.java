package com.didacusabella.yaspl.semantic;

public class VariableSymbol extends SemanticSymbol {

    public VariableSymbol(ReturnType returnType) {
        super(returnType);
    }

    @Override
    public String toString() {
        return String.format("Tipo:%s", this.getReturnType().getValue());
    }
}
