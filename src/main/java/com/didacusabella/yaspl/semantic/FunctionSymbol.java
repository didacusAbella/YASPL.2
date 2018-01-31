package com.didacusabella.yaspl.semantic;

public class FunctionSymbol extends SemanticSymbol {

   private String inputDomain;
   private String outputDomain;

    public FunctionSymbol(ReturnType returnType, String inputDomain, String outputDomain) {
        super(returnType);
        this.inputDomain = inputDomain;
        this.outputDomain = outputDomain;
    }

    public String getInputDomain() {
        return inputDomain;
    }

    public void setInputDomain(String inputDomain) {
        this.inputDomain = inputDomain;
    }

    public String getOutputDomain() {
        return outputDomain;
    }

    public void setOutputDomain(String outputDomain) {
        this.outputDomain = outputDomain;
    }

    public int getDomainSize(){
        return this.inputDomain.split("x").length;
    }

    public int getCodomainSize(){
        return this.outputDomain.split("x").length;
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
