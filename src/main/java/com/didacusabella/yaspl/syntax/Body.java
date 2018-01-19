package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;
import java.util.List;

public class Body extends YasplNode {

    private final List<VariableDeclaration> variableDeclarations;
    private final List<Statement> statements;

    public Body(List<VariableDeclaration> variableDeclarations, List<Statement> statements) {
        this.variableDeclarations = variableDeclarations;
        this.statements = statements;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<VariableDeclaration> getVariableDeclarationList() {
        return this.variableDeclarations;
    }

    public List<Statement> getStatements() {
        return this.statements;
    }
}
