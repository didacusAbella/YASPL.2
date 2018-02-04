package com.didacusabella.yaspl.semantic;

/**
 * Variable symbol is used to handle variable typr
 */
public class VariableSymbol extends SemanticSymbol {

    private VariableType varType;

    /**
     * Create a new variable symbol
     * @param returnType the type of variable
     */
    public VariableSymbol(ReturnType returnType) {
        super(returnType);
        this.varType = VariableType.NORMAL;
    }

    /**
     * Get the variable kind of this symbol
     * @return
     */
    public VariableType getVarType() {
        return varType;
    }

    /**
     * Set a new variable kind for this symbol
     * @param varType
     */
    public void setVarType(VariableType varType) {
        this.varType = varType;
    }

    @Override
    public String toString() {
        return String.format("Tipo:%s", this.getReturnType().getValue());
    }
}
