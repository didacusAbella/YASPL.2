package com.didacusabella.yaspl.syntax;

import com.didacusabella.yaspl.visitor.Visitor;

import java.util.List;

public class CompositeStatement extends Statement {

    private final List<Statement> statements;

    public CompositeStatement(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public <T, P> T accept(Visitor<T, P> visitor, P param) {
        return visitor.visit(this, param);
    }

    public List<Statement> getStatementList() {
        return this.statements;
    }

}
