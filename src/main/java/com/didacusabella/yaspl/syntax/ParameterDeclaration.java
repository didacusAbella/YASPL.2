package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ParameterDeclaration extends YasplNode {

    private final List<VariableDeclaration> variableDeclarations;

    public ParameterDeclaration(List<VariableDeclaration> variableDeclarations) {
        this.variableDeclarations = variableDeclarations;
    }

    public ParameterDeclaration(VariableDeclaration vd) {
        this.variableDeclarations = new ArrayList<>();
        this.variableDeclarations.add(vd);
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<VariableDeclaration> getVariableDeclarationList() {
        return this.variableDeclarations;
    }

}
