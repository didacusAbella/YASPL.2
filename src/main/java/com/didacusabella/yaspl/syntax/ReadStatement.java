package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class ReadStatement extends Statement {

    private final List<Variable> variables;
    private final List<Type> types;

    public ReadStatement(List<Variable> variables, List<Type> types) {
        this.variables = variables;
        this.types = types;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<Variable> getIdentifierList() {
        return this.variables;
    }

    public List<Type> getTypeList() {
        return this.types;
    }
}
