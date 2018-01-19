package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class FunctionCall extends Statement {

    private final Identifier identifier;
    private final List<Expression> expressions;
    private final List<Variable> variables;

    public FunctionCall(Identifier identifier, List<Expression> expressions, List<Variable> variables) {
        this.identifier = identifier;
        this.expressions = expressions;
        this.variables = variables;
    }

    public Identifier getFunctionName() {
        return this.identifier;
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }

    public List<Variable> getVariableList() {
        return this.variables;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }


}
