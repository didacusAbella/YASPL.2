package com.didacusabella.yaspl.semantic;

public class VariableSymbol extends SemanticSymbol {

    private VariableType varType;

    public VariableSymbol(ReturnType returnType) {
        super(returnType);
        this.varType = VariableType.NORMAL;
    }

    public VariableType getVarType() {
        return varType;
    }

    public void setVarType(VariableType varType) {
        this.varType = varType;
    }

    @Override
    public String toString() {
        return String.format("Tipo:%s", this.getReturnType().getValue());
    }
}
