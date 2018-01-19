package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class VariableDeclaration extends Decl {

    private final Type type;
    private final List<Variable> variables;

    public VariableDeclaration(Type type, List<Variable> variables) {
        this.type = type;
        this.variables = variables;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public Type getType() {
        return this.type;
    }

    public List<Variable> getVariables() {
        return this.variables;
    }
}
