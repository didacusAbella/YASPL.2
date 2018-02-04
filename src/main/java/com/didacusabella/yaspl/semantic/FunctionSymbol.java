package com.didacusabella.yaspl.semantic;

/**
 * This symbol is used to track when a function is addedd to symbol table
 */
public class FunctionSymbol extends SemanticSymbol {

   private String inputDomain;
   private String outputDomain;

    /**
     * Create a new function symbol
     * @param returnType the return type. Always be VOID after semantic analisys
     * @param inputDomain the domain of the function
     * @param outputDomain the codomain of the function
     */
    public FunctionSymbol(ReturnType returnType, String inputDomain, String outputDomain) {
        super(returnType);
        this.inputDomain = inputDomain;
        this.outputDomain = outputDomain;
    }

    /**
     * Get the input domain
     * @return the input domain
     */
    public String getInputDomain() {
        return inputDomain;
    }

    /**
     * give a new input domain
     * @param inputDomain the new domain
     */
    public void setInputDomain(String inputDomain) {
        this.inputDomain = inputDomain;
    }

    /**
     * get the output domain
     * @return the output domain
     */
    public String getOutputDomain() {
        return outputDomain;
    }

    /**
     * give a new output domain
     * @param outputDomain the new output domain
     */
    public void setOutputDomain(String outputDomain) {
        this.outputDomain = outputDomain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo:".concat(this.getReturnType().getValue()).concat("\n"));
        sb.append("Dominio:".concat(this.inputDomain));
        sb.append("Codominio:".concat(this.outputDomain));
        return sb.toString();
    }
}
